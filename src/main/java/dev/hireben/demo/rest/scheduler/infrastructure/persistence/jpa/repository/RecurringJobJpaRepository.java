package dev.hireben.demo.rest.scheduler.infrastructure.persistence.jpa.repository;

import dev.hireben.demo.rest.scheduler.infrastructure.persistence.jpa.entity.RecurringJobJpaEntity;
import dev.hireben.demo.rest.scheduler.infrastructure.persistence.jpa.repository.base.WebhookJobJpaRepository;

public interface RecurringJobJpaRepository extends WebhookJobJpaRepository<RecurringJobJpaEntity> {
}
