package dev.hireben.demo.rest.scheduler.domain.repository;

import dev.hireben.demo.rest.scheduler.domain.entity.JobExecutionRecord;

public interface JobExecutionRecordRepository {

  void save(JobExecutionRecord record);

}
