package dev.hireben.demo.rest.scheduler.infrastructure.persistence.repository;

import java.util.Collection;

import org.springframework.stereotype.Repository;

import dev.hireben.demo.rest.scheduler.domain.entity.RecurringJob;
import dev.hireben.demo.rest.scheduler.infrastructure.persistence.jpa.entity.RecurringJobEntity;
import dev.hireben.demo.rest.scheduler.infrastructure.persistence.jpa.mapper.RecurringJobEntityMapper;
import dev.hireben.demo.rest.scheduler.infrastructure.persistence.jpa.repository.RecurringJobJpaRepository;
import dev.hireben.demo.rest.scheduler.infrastructure.persistence.jpa.repository.WebhookContentJpaRepository;
import dev.hireben.demo.rest.scheduler.infrastructure.persistence.quartz.repository.WebhookJobQuartzRepository;
import dev.hireben.demo.rest.scheduler.infrastructure.persistence.repository.base.WebhookJobRepositoryImpl;

@Repository
public class RecurringJobRepositoryImpl
    extends WebhookJobRepositoryImpl<RecurringJobEntity, RecurringJob, RecurringJobJpaRepository> {

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
  protected Long extractId(RecurringJobEntity entity) {
    return entity.getId();
  }

  // ---------------------------------------------------------------------------//

  @Override
  protected RecurringJobEntity toEntity(RecurringJob domain) {
    return RecurringJobEntityMapper.toEntity(domain);
  }

  // ---------------------------------------------------------------------------//

  @Override
  protected RecurringJob toDomain(RecurringJobEntity entity, boolean includeContent) {
    return RecurringJobEntityMapper.toDomain(entity, includeContent);
  }

  // ---------------------------------------------------------------------------//

  @Override
  protected void scheduleJobs(Collection<RecurringJobEntity> entities) {
    scheduler.scheduleAllRecurringJobs(entities);
  }

}
