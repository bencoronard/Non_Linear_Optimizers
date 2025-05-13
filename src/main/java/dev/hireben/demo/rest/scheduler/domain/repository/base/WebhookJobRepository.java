package dev.hireben.demo.rest.scheduler.domain.repository.base;

import java.util.Collection;
import java.util.Optional;

import dev.hireben.demo.rest.scheduler.domain.entity.base.WebhookJob;

public interface WebhookJobRepository<T extends WebhookJob> {

  Collection<Long> saveAll(Collection<T> entities);

  Optional<T> findById(Long id);

  Optional<T> findByIdNoContent(Long id);

}
