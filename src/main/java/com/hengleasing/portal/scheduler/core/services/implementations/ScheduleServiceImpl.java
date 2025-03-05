package com.hengleasing.portal.scheduler.core.services.implementations;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hengleasing.portal.scheduler.core.dtos.CreateCronScheduleDTO;
import com.hengleasing.portal.scheduler.core.dtos.CreateOneTimeScheduleDTO;
import com.hengleasing.portal.scheduler.core.dtos.CronJobDTO;
import com.hengleasing.portal.scheduler.core.dtos.JobDTO;
import com.hengleasing.portal.scheduler.core.dtos.OneTimeJobDTO;
import com.hengleasing.portal.scheduler.core.dtos.ScheduleDTO;
import com.hengleasing.portal.scheduler.core.dtos.ScheduleDetails;
import com.hengleasing.portal.scheduler.core.entities.Schedule;
import com.hengleasing.portal.scheduler.core.entities.schedules.CronSchedule;
import com.hengleasing.portal.scheduler.core.entities.schedules.OneTimeSchedule;
import com.hengleasing.portal.scheduler.core.entities.schedules.CronSchedule.CronExecutionStatus;
import com.hengleasing.portal.scheduler.core.entities.schedules.OneTimeSchedule.OneTimeExecutionStatus;
import com.hengleasing.portal.scheduler.core.repositories.CronScheduleRepository;
import com.hengleasing.portal.scheduler.core.repositories.JobRepository;
import com.hengleasing.portal.scheduler.core.repositories.OneTimeScheduleRepository;
import com.hengleasing.portal.scheduler.core.services.ScheduleService;
import com.hengleasing.portal.scheduler.core.utilities.JobDataUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

  // ---------------------------------------------------------------------------//
  // Dependencies
  // ---------------------------------------------------------------------------//

  private final OneTimeScheduleRepository oneTimeScheduleRepository;
  private final CronScheduleRepository cronScheduleRepository;
  private final JobRepository jobRepository;

  // ---------------------------------------------------------------------------//
  // Methods
  // ---------------------------------------------------------------------------//

  @Transactional
  @Override
  public Collection<ScheduleDTO> createOneTimeSchedules(Collection<CreateOneTimeScheduleDTO> schedulesToCreate,
      String origin) {

    int batchSize = schedulesToCreate.size();
    if (batchSize == 0) {
      return Collections.emptyList();
    }

    Collection<OneTimeSchedule> schedulesToSave = new ArrayList<>(batchSize);
    schedulesToCreate.forEach(scheduleToCreate -> {
      ScheduleDetails scheduleDetails = scheduleToCreate.details();
      String refId = scheduleDetails.refId();
      String groupId = scheduleDetails.groupId();
      Boolean ignoreMisfire = scheduleDetails.ignoreMisfire();

      String genId = UUID.randomUUID().toString();

      OneTimeSchedule scheduleToSave = OneTimeSchedule.builder()
          .origin(origin)
          .refId(refId != null ? refId : genId)
          .groupId(groupId != null ? groupId : genId)
          .ignoreMisfire(ignoreMisfire != null ? ignoreMisfire : true)
          .createdAt(Instant.now())
          .destination(scheduleDetails.destination())
          .path(scheduleDetails.path())
          .payload(scheduleDetails.payload())
          .executionNote(null)
          .fireAt(Instant.ofEpochSecond(scheduleToCreate.fireAt()))
          .executedAt(null)
          .status(OneTimeExecutionStatus.SCHEDULED)
          .build();

      schedulesToSave.add(scheduleToSave);
    });

    Collection<OneTimeSchedule> savedOneTimeSchedules = oneTimeScheduleRepository.saveAll(schedulesToSave);

    Collection<OneTimeJobDTO> jobsToSave = new ArrayList<>(savedOneTimeSchedules.size());
    savedOneTimeSchedules.forEach(savedOneTimeSchedule -> {
      OneTimeJobDTO jobToSave = OneTimeJobDTO.builder()
          .id(savedOneTimeSchedule.getId())
          .refId(JobDataUtil.formatJobId(origin, JobDataUtil.JOB_TYPE_ONETIME,
              savedOneTimeSchedule.getRefId()))
          .groupId(JobDataUtil.formatJobId(origin, JobDataUtil.JOB_TYPE_ONETIME,
              savedOneTimeSchedule.getGroupId()))
          .ignoreMisfire(savedOneTimeSchedule.getIgnoreMisfire())
          .fireAt(savedOneTimeSchedule.getFireAt())
          .build();
      jobsToSave.add(jobToSave);
    });

    jobRepository.saveAllOneTimeJobs(jobsToSave);

    return scheduleDTOs(schedulesToSave);
  }

  // ---------------------------------------------------------------------------//

  @Transactional
  @Override
  public Collection<ScheduleDTO> createCronSchedules(Collection<CreateCronScheduleDTO> schedulesToCreate,
      String origin) {

    int batchSize = schedulesToCreate.size();
    if (batchSize == 0) {
      return Collections.emptyList();
    }

    Collection<CronSchedule> schedulesToSave = new ArrayList<>(batchSize);
    schedulesToCreate.forEach(scheduleToCreate -> {
      ScheduleDetails scheduleDetails = scheduleToCreate.details();
      String refId = scheduleDetails.refId();
      String groupId = scheduleDetails.groupId();
      Boolean ignoreMisfire = scheduleDetails.ignoreMisfire();

      String genId = UUID.randomUUID().toString();

      CronSchedule scheduleToSave = CronSchedule.builder()
          .origin(origin)
          .refId(refId != null ? refId : genId)
          .groupId(groupId != null ? groupId : genId)
          .ignoreMisfire(ignoreMisfire != null ? ignoreMisfire : true)
          .createdAt(Instant.now())
          .destination(scheduleDetails.destination())
          .path(scheduleDetails.path())
          .payload(scheduleDetails.payload())
          .executionNote(null)
          .expression(scheduleToCreate.expression())
          .isActive(true)
          .lastExecutedAt(null)
          .status(CronExecutionStatus.ACTIVE)
          .build();
      schedulesToSave.add(scheduleToSave);
    });

    Collection<CronSchedule> savedCronSchedules = cronScheduleRepository.saveAll(schedulesToSave);

    Collection<CronJobDTO> jobsToSave = new ArrayList<>(savedCronSchedules.size());
    savedCronSchedules.forEach(savedCronSchedule -> {
      CronJobDTO jobToSave = CronJobDTO.builder()
          .id(savedCronSchedule.getId())
          .refId(JobDataUtil.formatJobId(origin, JobDataUtil.JOB_TYPE_CRON, savedCronSchedule.getRefId()))
          .groupId(
              JobDataUtil.formatJobId(origin, JobDataUtil.JOB_TYPE_CRON, savedCronSchedule.getGroupId()))
          .ignoreMisfire(savedCronSchedule.getIgnoreMisfire())
          .expression(savedCronSchedule.getExpression())
          .build();
      jobsToSave.add(jobToSave);
    });

    jobRepository.saveAllCronJobs(jobsToSave);

    return scheduleDTOs(schedulesToSave);
  }

  // ---------------------------------------------------------------------------//

  @Override
  public ScheduleDTO deleteOneTimeScheduleWithRefId(String refId, String origin) {

    OneTimeSchedule scheduleToDelete = oneTimeScheduleRepository.findByRefIdAndOrigin(refId, origin).orElse(null);

    if (scheduleToDelete == null) {
      return null;
    }

    oneTimeScheduleRepository.delete(scheduleToDelete);

    JobDTO jobToDelete = JobDTO.builder()
        .refId(JobDataUtil.formatJobId(origin, JobDataUtil.JOB_TYPE_ONETIME, refId))
        .groupId(
            JobDataUtil.formatJobId(origin, JobDataUtil.JOB_TYPE_ONETIME, scheduleToDelete.getGroupId()))
        .build();

    jobRepository.deleteAllJobs(List.of(jobToDelete));

    return scheduleDTO(scheduleToDelete);
  }

  // ---------------------------------------------------------------------------//

  @Override
  public ScheduleDTO deleteCronScheduleWithRefId(String refId, String origin) {

    CronSchedule scheduleToDelete = cronScheduleRepository.findByRefIdAndOrigin(refId, origin).orElse(null);

    if (scheduleToDelete == null) {
      return null;
    }

    cronScheduleRepository.delete(scheduleToDelete);

    JobDTO jobToDelete = JobDTO.builder()
        .refId(JobDataUtil.formatJobId(origin, JobDataUtil.JOB_TYPE_CRON, refId))
        .groupId(JobDataUtil.formatJobId(origin, JobDataUtil.JOB_TYPE_CRON, scheduleToDelete.getGroupId()))
        .build();

    jobRepository.deleteAllJobs(List.of(jobToDelete));

    return scheduleDTO(scheduleToDelete);
  }

  // ---------------------------------------------------------------------------//

  @Override
  public Collection<ScheduleDTO> deleteAllOneTimeSchedulesWithGroupId(String groupId, String origin) {

    Collection<OneTimeSchedule> schedulesToDelete = oneTimeScheduleRepository.findAllByGroupIdAndOrigin(groupId,
        origin);

    if (schedulesToDelete.isEmpty()) {
      return Collections.emptyList();
    }

    oneTimeScheduleRepository.deleteAll(schedulesToDelete);

    Collection<JobDTO> jobsToDelete = new ArrayList<>(schedulesToDelete.size());
    schedulesToDelete.forEach(scheduleToDelete -> {
      JobDTO jobToDelete = JobDTO.builder()
          .refId(JobDataUtil.formatJobId(origin, JobDataUtil.JOB_TYPE_ONETIME, scheduleToDelete.getRefId()))
          .groupId(JobDataUtil.formatJobId(origin, JobDataUtil.JOB_TYPE_ONETIME, groupId))
          .build();
      jobsToDelete.add(jobToDelete);
    });

    jobRepository.deleteAllJobs(jobsToDelete);

    return scheduleDTOs(schedulesToDelete);
  }

  // ---------------------------------------------------------------------------//

  @Override
  public Collection<ScheduleDTO> deleteAllCronSchedulesWithGroupId(String groupId, String origin) {

    Collection<CronSchedule> schedulesToDelete = cronScheduleRepository.findAllByGroupIdAndOrigin(groupId, origin);

    if (schedulesToDelete.isEmpty()) {
      return Collections.emptyList();
    }

    cronScheduleRepository.deleteAll(schedulesToDelete);

    Collection<JobDTO> jobsToDelete = new ArrayList<>(schedulesToDelete.size());
    schedulesToDelete.forEach(scheduleToDelete -> {
      JobDTO jobToDelete = JobDTO.builder()
          .refId(JobDataUtil.formatJobId(origin, JobDataUtil.JOB_TYPE_CRON, scheduleToDelete.getRefId()))
          .groupId(JobDataUtil.formatJobId(origin, JobDataUtil.JOB_TYPE_CRON, groupId))
          .build();
      jobsToDelete.add(jobToDelete);
    });

    jobRepository.deleteAllJobs(jobsToDelete);

    return scheduleDTOs(schedulesToDelete);
  }

  // ---------------------------------------------------------------------------//
  // Helpers
  // ---------------------------------------------------------------------------//

  private ScheduleDTO scheduleDTO(Schedule schedule) {
    return ScheduleDTO.builder()
        .id(schedule.getId())
        .refId(schedule.getRefId())
        .groupId(schedule.getGroupId())
        .build();
  }

  // ---------------------------------------------------------------------------//

  private Collection<ScheduleDTO> scheduleDTOs(Collection<? extends Schedule> schedules) {
    Collection<ScheduleDTO> scheduleDTOs = new ArrayList<>(schedules.size());
    schedules.forEach(schedule -> scheduleDTOs.add(scheduleDTO(schedule)));
    return scheduleDTOs;
  }

}
