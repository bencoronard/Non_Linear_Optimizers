package dev.hireben.demo.rest.scheduler.infrastructure.persistence.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.hireben.demo.rest.scheduler.infrastructure.persistence.jpa.entity.OneTimeJobEntity;

public interface OneTimeJobEntityRepository extends JpaRepository<OneTimeJobEntity, Long> {
}
