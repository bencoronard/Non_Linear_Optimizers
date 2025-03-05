package com.hengleasing.portal.scheduler.core.repositories;

import java.util.Collection;

import com.hengleasing.portal.scheduler.core.dtos.CronJobDTO;
import com.hengleasing.portal.scheduler.core.dtos.JobDTO;
import com.hengleasing.portal.scheduler.core.dtos.OneTimeJobDTO;

public interface JobRepository {

  Collection<OneTimeJobDTO> saveAllOneTimeJobs(Collection<OneTimeJobDTO> jobsToCreate);

  Collection<CronJobDTO> saveAllCronJobs(Collection<CronJobDTO> jobsToCreate);

  Collection<JobDTO> deleteAllJobs(Collection<JobDTO> jobsToDelete);

}
