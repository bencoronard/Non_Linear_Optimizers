package dev.hireben.demo.rest.scheduler.domain.repository;

import dev.hireben.demo.rest.scheduler.domain.entity.JobExecRecord;

public interface JobExecRecordRepository {

  void save(JobExecRecord record);

}
