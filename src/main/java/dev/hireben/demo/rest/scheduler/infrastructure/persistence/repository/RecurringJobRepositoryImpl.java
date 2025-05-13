package dev.hireben.demo.rest.scheduler.infrastructure.persistence.repository;

import java.util.Collection;

import org.springframework.stereotype.Repository;

import dev.hireben.demo.rest.scheduler.domain.entity.RecurringJob;
import dev.hireben.demo.rest.scheduler.infrastructure.persistence.jpa.entity.RecurringJobJpaEntity;
import dev.hireben.demo.rest.scheduler.infrastructure.persistence.jpa.mapper.RecurringJobJpaMapper;
import dev.hireben.demo.rest.scheduler.infrastructure.persistence.jpa.repository.RecurringJobJpaRepository;
import dev.hireben.demo.rest.scheduler.infrastructure.persistence.jpa.repository.WebhookContentJpaRepository;
import dev.hireben.demo.rest.scheduler.infrastructure.persistence.quartz.repository.WebhookJobQuartzRepository;
import dev.hireben.demo.rest.scheduler.infrastructure.persistence.repository.base.WebhookJobRepositoryImpl;

@Repository
public class RecurringJobRepositoryImpl
    extends WebhookJobRepositoryImpl<RecurringJobJpaEntity, RecurringJob, RecurringJobJpaRepository> {

  // ---------------------------------------------------------------------------//
  // Constructors
  // ---------------------------------------------------------------------------//

  protected RecurringJobRepositoryImpl(WebhookJobQuartzRepository scheduler,
      WebhookContentJpaRepository webhookRepository, RecurringJobJpaRepository jobRepository) {
    super(scheduler, webhookRepository, jobRepository);
  }

  // ---------------------------------------------------------------------------//
  // Methods
  // ---------------------------------------------------------------------------//

  @Override
  protected Long extractId(RecurringJobJpaEntity entity) {
    return entity.getId();
  }

  // ---------------------------------------------------------------------------//

  @Override
  protected RecurringJobJpaEntity toEntity(RecurringJob domain) {
    return RecurringJobJpaMapper.toEntity(domain);
  }

  // ---------------------------------------------------------------------------//

  @Override
  protected RecurringJob toDomain(RecurringJobJpaEntity entity, boolean includeContent) {
    return RecurringJobJpaMapper.toDomain(entity, includeContent);
  }

  // ---------------------------------------------------------------------------//

  @Override
  protected void scheduleJobs(Collection<RecurringJob> jobs) {
    scheduler.scheduleAllRecurringJobs(jobs);
  }

}
