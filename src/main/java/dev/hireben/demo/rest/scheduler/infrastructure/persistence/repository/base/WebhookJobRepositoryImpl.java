package dev.hireben.demo.rest.scheduler.infrastructure.persistence.repository.base;

import java.util.Optional;

import dev.hireben.demo.rest.scheduler.domain.entity.base.WebhookJob;
import dev.hireben.demo.rest.scheduler.domain.repository.base.WebhookJobRepository;
import dev.hireben.demo.rest.scheduler.infrastructure.persistence.jpa.repository.base.WebhookJobJpaRepository;
import dev.hireben.demo.rest.scheduler.infrastructure.persistence.quartz.repository.WebhookJobQuartzRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class WebhookJobRepositoryImpl<T, D extends WebhookJob, R extends WebhookJobJpaRepository<T, Long>>
    implements WebhookJobRepository<D> {

  // ---------------------------------------------------------------------------//
  // Fields
  // ---------------------------------------------------------------------------//

  protected final WebhookJobQuartzRepository scheduler;
  protected final R repository;

  // ---------------------------------------------------------------------------//
  // Methods
  // ---------------------------------------------------------------------------//

  public void test() {
    scheduler.scheduleAllOneTimeJobs(null);
    repository.findById(2L);
  }

  // ---------------------------------------------------------------------------//

  @Override
  public Optional<byte[]> retrievePayloadByJobId(Long id) {
    return null;
  }

}
