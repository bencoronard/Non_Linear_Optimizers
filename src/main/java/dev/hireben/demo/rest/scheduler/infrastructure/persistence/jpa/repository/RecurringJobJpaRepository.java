package dev.hireben.demo.rest.scheduler.infrastructure.persistence.jpa.repository;

import dev.hireben.demo.rest.scheduler.infrastructure.persistence.jpa.entity.RecurringJobEntity;
import dev.hireben.demo.rest.scheduler.infrastructure.persistence.jpa.repository.base.WebhookJobJpaRepository;

public interface RecurringJobJpaRepository extends WebhookJobJpaRepository<RecurringJobEntity, Long> {
}
