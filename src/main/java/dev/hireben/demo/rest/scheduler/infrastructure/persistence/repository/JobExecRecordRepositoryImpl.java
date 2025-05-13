package dev.hireben.demo.rest.scheduler.infrastructure.persistence.repository;

import org.springframework.stereotype.Repository;

import dev.hireben.demo.rest.scheduler.domain.entity.JobExecRecord;
import dev.hireben.demo.rest.scheduler.domain.repository.JobExecRecordRepository;
import dev.hireben.demo.rest.scheduler.infrastructure.persistence.jpa.mapper.JobExecRecordJpaMapper;
import dev.hireben.demo.rest.scheduler.infrastructure.persistence.jpa.repository.JobExecRecordJpaRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
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
    repository.save(JobExecRecordJpaMapper.toEntity(record));
  }

}
