package dev.hireben.demo.rest.scheduler.domain.repository;

import dev.hireben.demo.rest.scheduler.domain.entity.OneTimeJob;
import dev.hireben.demo.rest.scheduler.domain.repository.base.WebhookJobRepository;

public interface OneTimeJobRepository extends WebhookJobRepository<OneTimeJob> {
}
