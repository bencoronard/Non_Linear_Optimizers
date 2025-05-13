package dev.hireben.demo.rest.scheduler.infrastructure.persistence.repository;

import java.util.Collection;

import org.springframework.stereotype.Repository;

import dev.hireben.demo.rest.scheduler.domain.entity.OneTimeJob;
import dev.hireben.demo.rest.scheduler.infrastructure.persistence.jpa.entity.OneTimeJobJpaEntity;
import dev.hireben.demo.rest.scheduler.infrastructure.persistence.jpa.mapper.OneTimeJobJpaMapper;
import dev.hireben.demo.rest.scheduler.infrastructure.persistence.jpa.repository.OneTimeJobJpaRepository;
import dev.hireben.demo.rest.scheduler.infrastructure.persistence.jpa.repository.WebhookContentJpaRepository;
import dev.hireben.demo.rest.scheduler.infrastructure.persistence.quartz.repository.WebhookJobQuartzRepository;
import dev.hireben.demo.rest.scheduler.infrastructure.persistence.repository.base.WebhookJobRepositoryImpl;

@Repository
public class OneTimeJobRepositoryImpl
    extends WebhookJobRepositoryImpl<OneTimeJobJpaEntity, OneTimeJob, OneTimeJobJpaRepository> {

  // ---------------------------------------------------------------------------//
  // Constructors
  // ---------------------------------------------------------------------------//

  protected OneTimeJobRepositoryImpl(WebhookJobQuartzRepository scheduler,
      WebhookContentJpaRepository webhookRepository, OneTimeJobJpaRepository jobRepository) {
    super(scheduler, webhookRepository, jobRepository);
  }

  // ---------------------------------------------------------------------------//
  // Methods
  // ---------------------------------------------------------------------------//

  @Override
  protected Long extractId(OneTimeJobJpaEntity entity) {
    return entity.getId();
  }

  // ---------------------------------------------------------------------------//

  @Override
  protected OneTimeJobJpaEntity toEntity(OneTimeJob domain) {
    return OneTimeJobJpaMapper.toEntity(domain);
  }

  // ---------------------------------------------------------------------------//

  @Override
  protected OneTimeJob toDomain(OneTimeJobJpaEntity entity, boolean includeContent) {
    return OneTimeJobJpaMapper.toDomain(entity, includeContent);
  }

  // ---------------------------------------------------------------------------//

  @Override
  protected void scheduleJobs(Collection<OneTimeJob> jobs) {
    scheduler.scheduleAllOneTimeJobs(jobs);
  }

}
