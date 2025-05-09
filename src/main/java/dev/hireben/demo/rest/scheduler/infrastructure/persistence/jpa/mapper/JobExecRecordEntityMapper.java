package dev.hireben.demo.rest.scheduler.infrastructure.persistence.jpa.mapper;

import dev.hireben.demo.rest.scheduler.domain.entity.JobExecRecord;
import dev.hireben.demo.rest.scheduler.infrastructure.persistence.jpa.entity.JobExecRecordEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class JobExecRecordEntityMapper {

  // ---------------------------------------------------------------------------//
  // Methods
  // ---------------------------------------------------------------------------//

  public JobExecRecordEntity toEntity(JobExecRecord domain) {
    return JobExecRecordEntity.builder()
        .id(domain.getId())
        .jobId(domain.getJobId())
        .executedAt(domain.getExecutedAt())
        .execResult(domain.getExecResult())
        .build();
  }

  // ---------------------------------------------------------------------------//

  public JobExecRecord toDomain(JobExecRecordEntity entity) {
    return JobExecRecord.builder()
        .id(entity.getId())
        .jobId(entity.getJobId())
        .executedAt(entity.getExecutedAt())
        .execResult(entity.getExecResult())
        .build();
  }

}
