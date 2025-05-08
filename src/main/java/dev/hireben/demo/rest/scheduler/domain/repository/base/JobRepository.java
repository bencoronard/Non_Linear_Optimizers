package dev.hireben.demo.rest.scheduler.domain.repository.base;

import java.util.Collection;
import java.util.Optional;

import dev.hireben.demo.rest.scheduler.domain.entity.base.WebhookJob;

public interface JobRepository<T extends WebhookJob> {

  Collection<Long> saveAll(Collection<T> entities);

  Optional<byte[]> retrievePayloadByJobId(Long id);

}
