package dev.hireben.demo.rest.scheduler.infrastructure.persistence.quartz.entity;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import dev.hireben.demo.rest.scheduler.domain.repository.JobExecRecordRepository;
import dev.hireben.demo.rest.scheduler.domain.repository.RecurringJobRepository;
import dev.hireben.demo.rest.scheduler.domain.service.WebhookDispatchService;
import dev.hireben.demo.rest.scheduler.infrastructure.persistence.quartz.entity.base.WebhookJobQuartzEntity;

@Component
@Scope("prototype")
public class RecurringJobQuartzEntity extends WebhookJobQuartzEntity<RecurringJobRepository> {

  // ---------------------------------------------------------------------------//
  // Constructors
  // ---------------------------------------------------------------------------//

  protected RecurringJobQuartzEntity(WebhookDispatchService dispatcher, JobExecRecordRepository recordRepository,
      RecurringJobRepository jobRepository) {
    super(dispatcher, recordRepository, jobRepository);
  }

}
