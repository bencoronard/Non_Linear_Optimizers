package dev.hireben.demo.rest.scheduler.infrastructure.persistence.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.hireben.demo.rest.scheduler.infrastructure.persistence.jpa.entity.JobExecRecordJpaEntity;

public interface JobExecRecordJpaRepository extends JpaRepository<JobExecRecordJpaEntity, Long> {
}
