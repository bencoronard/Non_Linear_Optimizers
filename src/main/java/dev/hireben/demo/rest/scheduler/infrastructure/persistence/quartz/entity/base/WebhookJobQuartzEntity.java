package dev.hireben.demo.rest.scheduler.infrastructure.persistence.quartz.entity.base;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.lang.NonNull;
import org.springframework.scheduling.quartz.QuartzJobBean;

import dev.hireben.demo.rest.scheduler.domain.entity.base.WebhookJob;
import dev.hireben.demo.rest.scheduler.domain.repository.JobExecRecordRepository;
import dev.hireben.demo.rest.scheduler.domain.repository.base.WebhookJobRepository;
import dev.hireben.demo.rest.scheduler.domain.service.WebhookDispatchService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class WebhookJobQuartzEntity<R extends WebhookJobRepository<? extends WebhookJob>>
    extends QuartzJobBean {

  // ---------------------------------------------------------------------------//
  // Fields
  // ---------------------------------------------------------------------------//

  private final WebhookDispatchService dispatcher;
  private final JobExecRecordRepository recordRepository;
  private final R jobRepository;
  private Long jobId;

  // ---------------------------------------------------------------------------//
  // Methods
  // ---------------------------------------------------------------------------//

  @Override
  public void executeInternal(@NonNull JobExecutionContext context) throws JobExecutionException {
    jobRepository.findById(jobId).ifPresent(job -> job.execute(dispatcher, recordRepository));
  }

}
