package dev.hireben.demo.rest.scheduler.infrastructure.persistence.quartz.entity;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.annotation.Scope;
import org.springframework.lang.NonNull;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import dev.hireben.demo.rest.scheduler.domain.repository.JobExecRecordRepository;
import dev.hireben.demo.rest.scheduler.domain.repository.OneTimeJobRepository;
import dev.hireben.demo.rest.scheduler.domain.service.DispatchService;
import lombok.RequiredArgsConstructor;

@Component
@Scope("prototype")
@RequiredArgsConstructor
public class OneTimeJobQuartzEntity extends QuartzJobBean {

  // ---------------------------------------------------------------------------//
  // Fields
  // ---------------------------------------------------------------------------//

  private final DispatchService dispatcher;
  private final JobExecRecordRepository recordRepository;
  private final OneTimeJobRepository jobRepository;
  private Long jobId;

  // ---------------------------------------------------------------------------//
  // Methods
  // ---------------------------------------------------------------------------//

  @Override
  protected void executeInternal(@NonNull JobExecutionContext context) throws JobExecutionException {
    jobRepository.findById(jobId).ifPresent(job -> job.execute(dispatcher, jobRepository, recordRepository));
  }

}
