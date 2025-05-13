package dev.hireben.demo.rest.scheduler.infrastructure.persistence.repository.base;

import java.util.Collection;
import java.util.Optional;
import dev.hireben.demo.rest.scheduler.domain.entity.base.WebhookJob;
import dev.hireben.demo.rest.scheduler.domain.repository.base.WebhookJobRepository;
import dev.hireben.demo.rest.scheduler.infrastructure.persistence.jpa.entity.base.WebhookJobJpaEntity;
import dev.hireben.demo.rest.scheduler.infrastructure.persistence.jpa.repository.WebhookContentJpaRepository;
import dev.hireben.demo.rest.scheduler.infrastructure.persistence.jpa.repository.base.WebhookJobJpaRepository;
import dev.hireben.demo.rest.scheduler.infrastructure.persistence.quartz.repository.WebhookJobQuartzRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class WebhookJobRepositoryImpl<T extends WebhookJobJpaEntity, D extends WebhookJob, R extends WebhookJobJpaRepository<T>>
    implements WebhookJobRepository<D> {

  // ---------------------------------------------------------------------------//
  // Fields
  // ---------------------------------------------------------------------------//

  protected final WebhookJobQuartzRepository scheduler;
  protected final WebhookContentJpaRepository webhookRepository;
  protected final R jobRepository;

  // ---------------------------------------------------------------------------//
  // Methods
  // ---------------------------------------------------------------------------//

  protected abstract Long extractId(T entity);

  protected abstract T toEntity(D domain);

  protected abstract D toDomain(T entity, boolean includeContent);

  protected abstract void scheduleJobs(Collection<D> jobs);

  // ---------------------------------------------------------------------------//

  @Override
  public Collection<Long> saveAll(Collection<D> jobs) {
    Collection<T> mappedJobs = jobs.stream().map(this::toEntity).toList();
    scheduleJobs(jobs);
    return jobRepository.saveAll(mappedJobs).stream().map(this::extractId).toList();
  }

  // ---------------------------------------------------------------------------//

  @Override
  public Optional<D> findById(Long id) {
    return jobRepository.findById(id).map(job -> toDomain(job, true));
  }

  // ---------------------------------------------------------------------------//

  @Override
  public Optional<D> findByIdNoContent(Long id) {
    return jobRepository.findById(id).map(job -> toDomain(job, false));
  }

}
