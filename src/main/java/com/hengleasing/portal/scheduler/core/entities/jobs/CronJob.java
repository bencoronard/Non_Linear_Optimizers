package com.hengleasing.portal.scheduler.core.entities.jobs;

import java.time.Instant;
import java.util.List;
import java.util.Map;

import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import com.hengleasing.portal.scheduler.common.DefaultValue;
import com.hengleasing.portal.scheduler.context.HttpHeaderKey;
import com.hengleasing.portal.scheduler.core.dtos.JobDTO;
import com.hengleasing.portal.scheduler.core.entities.ExecutionResult;
import com.hengleasing.portal.scheduler.core.entities.QuartzJob;
import com.hengleasing.portal.scheduler.core.entities.schedules.CronSchedule;
import com.hengleasing.portal.scheduler.core.entities.schedules.CronSchedule.CronExecutionStatus;
import com.hengleasing.portal.scheduler.core.exceptions.ScheduleNotFoundException;
import com.hengleasing.portal.scheduler.core.exceptions.ServiceNotInRegistryException;
import com.hengleasing.portal.scheduler.core.repositories.CronScheduleRepository;
import com.hengleasing.portal.scheduler.core.repositories.JobRepository;
import com.hengleasing.portal.scheduler.core.utilities.JobDataUtil;
import com.hengleasing.portal.scheduler.exceptions.HttpResponseException;
import com.hengleasing.portal.scheduler.exceptions.SeverityLevel;
import com.hengleasing.portal.scheduler.http.dtos.HttpClientResponse;
import com.hengleasing.portal.scheduler.http.services.HttpClientService;
import com.hengleasing.portal.scheduler.registry.entities.ServiceRegistry;
import com.hengleasing.portal.scheduler.registry.services.ServiceRegistryService;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@Scope("prototype")
@Setter
@RequiredArgsConstructor
public class CronJob extends QuartzJob {

  // ---------------------------------------------------------------------------//
  // Dependencies
  // ---------------------------------------------------------------------------//

  private final CronScheduleRepository scheduleRepository;
  private final JobRepository jobRepository;
  private final HttpClientService httpClientService;
  private final ServiceRegistryService serviceRegistry;

  // ---------------------------------------------------------------------------//
  // Fields
  // ---------------------------------------------------------------------------//

  @Value("${info.api.internal.service-id}")
  private String schedulerServiceId;

  private Long scheduleId;

  // ---------------------------------------------------------------------------//
  // Methods
  // ---------------------------------------------------------------------------//

  @Override
  protected void executeJobLogic(JobExecutionContext context) throws Exception {

    CronSchedule schedule = scheduleRepository.findById(scheduleId)
        .orElseThrow(() -> {
          throw new ScheduleNotFoundException(String.format("Cron schedule %s not found", scheduleId),
              SeverityLevel.MEDIUM);
        });

    if (!schedule.getIsActive()) {
      schedule.setStatus(CronExecutionStatus.TERMINATED);
      String origin = schedule.getOrigin();
      jobRepository.deleteAllJobs(List.of(JobDTO.builder()
          .refId(JobDataUtil.formatJobId(origin, JobDataUtil.JOB_TYPE_CRON, schedule.getRefId()))
          .groupId(JobDataUtil.formatJobId(origin, JobDataUtil.JOB_TYPE_CRON, schedule.getGroupId()))
          .build()));
      scheduleRepository.save(schedule);
      return;
    }

    String destinationId = schedule.getDestination();

    ServiceRegistry destination = serviceRegistry.retrieveServiceWithServiceId(destinationId)
        .orElseThrow(() -> {
          throw new ServiceNotInRegistryException(
              String.format("Destination with ID %s not registered", destinationId), SeverityLevel.MEDIUM);
        });

    String url = destination.getBaseUrl() + schedule.getPath();

    Map<String, String> headers = Map.of(
        HttpHeaderKey.API_KEY, destination.getSecretKey(),
        HttpHeaderKey.SERVICE_ID, schedulerServiceId,
        HttpHeaderKey.TRACE_ID, String.format("%s-%s", destination.getServiceId(), schedule.getId()));

    String payload = schedule.getPayload();

    try {

      HttpClientResponse<String> response = httpClientService.post(url, payload, headers);

      schedule.setExecutionNote(ExecutionResult.builder()
          .code(String.valueOf(response.code().value()))
          .message(response.body())
          .build());

    } catch (Exception e) {

      if (e instanceof HttpResponseException httpEx) {

        HttpClientResponse<String> response = httpEx.getResponse();

        schedule.setExecutionNote(ExecutionResult.builder()
            .code(String.valueOf(response.code().value()))
            .message(response.body())
            .build());

        return;
      }

      schedule.setExecutionNote(ExecutionResult.builder()
          .code(DefaultValue.RESP_CODE_UNKNOWN)
          .message(e.getMessage())
          .build());

      throw e;

    } finally {
      schedule.setLastExecutedAt(Instant.now());
      scheduleRepository.save(schedule);
    }
  }

}
