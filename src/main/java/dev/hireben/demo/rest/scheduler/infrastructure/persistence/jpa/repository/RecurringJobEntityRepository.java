package dev.hireben.demo.rest.scheduler.infrastructure.persistence.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.hireben.demo.rest.scheduler.infrastructure.persistence.jpa.entity.RecurringJobEntity;

public interface RecurringJobEntityRepository extends JpaRepository<RecurringJobEntity, Long> {
}
