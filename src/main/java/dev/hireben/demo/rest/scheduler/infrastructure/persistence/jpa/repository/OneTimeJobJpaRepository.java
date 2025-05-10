package dev.hireben.demo.rest.scheduler.infrastructure.persistence.jpa.repository;

import dev.hireben.demo.rest.scheduler.infrastructure.persistence.jpa.entity.OneTimeJobEntity;
import dev.hireben.demo.rest.scheduler.infrastructure.persistence.jpa.repository.base.WebhookJobJpaRepository;

public interface OneTimeJobJpaRepository extends WebhookJobJpaRepository<OneTimeJobEntity, Long> {
}
