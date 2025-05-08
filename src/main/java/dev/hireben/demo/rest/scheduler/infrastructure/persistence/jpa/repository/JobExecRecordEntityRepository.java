package dev.hireben.demo.rest.scheduler.infrastructure.persistence.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.hireben.demo.rest.scheduler.infrastructure.persistence.jpa.entity.JobExecRecordEntity;

public interface JobExecRecordEntityRepository extends JpaRepository<JobExecRecordEntity, Long> {
}
