package dev.hireben.demo.rest.scheduler.infrastructure.persistence.repository;

import org.springframework.stereotype.Repository;

import dev.hireben.demo.rest.scheduler.domain.entity.JobExecRecord;
import dev.hireben.demo.rest.scheduler.domain.repository.JobExecRecordRepository;
import dev.hireben.demo.rest.scheduler.infrastructure.persistence.jpa.mapper.JobExecRecordEntityMapper;
import dev.hireben.demo.rest.scheduler.infrastructure.persistence.jpa.repository.JobExecRecordJpaRepository;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class JobExecRecordRepositoryImpl implements JobExecRecordRepository {

  // ---------------------------------------------------------------------------//
  // Fields
  // ---------------------------------------------------------------------------//

  private final JobExecRecordJpaRepository repository;

  // ---------------------------------------------------------------------------//
  // Methods
  // ---------------------------------------------------------------------------//

  @Override
  public void save(JobExecRecord record) {
    repository.save(JobExecRecordEntityMapper.toEntity(record));
  }

}
