package dev.hireben.demo.rest.scheduler.infrastructure.persistence.quartz.entity;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import dev.hireben.demo.rest.scheduler.domain.repository.JobExecRecordRepository;
import dev.hireben.demo.rest.scheduler.domain.repository.OneTimeJobRepository;
import dev.hireben.demo.rest.scheduler.domain.service.WebhookDispatchService;
import dev.hireben.demo.rest.scheduler.infrastructure.persistence.quartz.entity.base.WebhookJobQuartzEntity;

@Component
@Scope("prototype")
public class OneTimeJobQuartzEntity extends WebhookJobQuartzEntity<OneTimeJobRepository> {

  // ---------------------------------------------------------------------------//
  // Constructors
  // ---------------------------------------------------------------------------//

  protected OneTimeJobQuartzEntity(WebhookDispatchService dispatcher, JobExecRecordRepository recordRepository,
      OneTimeJobRepository jobRepository) {
    super(dispatcher, recordRepository, jobRepository);
  }

}
