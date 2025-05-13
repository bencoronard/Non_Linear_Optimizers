package dev.hireben.demo.rest.scheduler.infrastructure.persistence.jpa.mapper;

import dev.hireben.demo.rest.scheduler.domain.entity.JobExecRecord;
import dev.hireben.demo.rest.scheduler.infrastructure.persistence.jpa.entity.JobExecRecordJpaEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class JobExecRecordJpaMapper {

  // ---------------------------------------------------------------------------//
  // Methods
  // ---------------------------------------------------------------------------//

  public JobExecRecordJpaEntity toEntity(JobExecRecord domain) {
    return JobExecRecordJpaEntity.builder()
        .id(domain.getId())
        .jobId(domain.getJobId())
        .executedAt(domain.getExecutedAt())
        .execResult(domain.getExecResult())
        .build();
  }

  // ---------------------------------------------------------------------------//

  public JobExecRecord toDomain(JobExecRecordJpaEntity entity) {
    return JobExecRecord.builder()
        .id(entity.getId())
        .jobId(entity.getJobId())
        .executedAt(entity.getExecutedAt())
        .execResult(entity.getExecResult())
        .build();
  }

}
