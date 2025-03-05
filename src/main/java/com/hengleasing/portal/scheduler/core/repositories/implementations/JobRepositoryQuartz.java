package com.hengleasing.portal.scheduler.core.repositories.implementations;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.stereotype.Component;

import com.hengleasing.portal.scheduler.core.dtos.CronJobDTO;
import com.hengleasing.portal.scheduler.core.dtos.JobDTO;
import com.hengleasing.portal.scheduler.core.dtos.OneTimeJobDTO;
import com.hengleasing.portal.scheduler.core.entities.jobs.CronJob;
import com.hengleasing.portal.scheduler.core.entities.jobs.OneTimeJob;
import com.hengleasing.portal.scheduler.core.exceptions.JobDeletionException;
import com.hengleasing.portal.scheduler.core.exceptions.JobPersistenceException;
import com.hengleasing.portal.scheduler.core.repositories.JobRepository;
import com.hengleasing.portal.scheduler.core.utilities.JobDataUtil;
import com.hengleasing.portal.scheduler.exceptions.SeverityLevel;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JobRepositoryQuartz implements JobRepository {

  // ---------------------------------------------------------------------------//
  // Dependencies
  // ---------------------------------------------------------------------------//

  private final Scheduler scheduler;

  // ---------------------------------------------------------------------------//
  // Methods
  // ---------------------------------------------------------------------------//

  @Override
  public Collection<OneTimeJobDTO> saveAllOneTimeJobs(Collection<OneTimeJobDTO> jobsToCreate) {
    Map<JobDetail, Set<? extends Trigger>> jobs = new HashMap<>(jobsToCreate.size());

    jobsToCreate.forEach(jobToCreate -> {
      String refId = jobToCreate.getRefId();
      String groupId = jobToCreate.getGroupId();

      SimpleScheduleBuilder simpleSchedule = SimpleScheduleBuilder.simpleSchedule().withRepeatCount(0);

      if (jobToCreate.getIgnoreMisfire()) {
        simpleSchedule.withMisfireHandlingInstructionNextWithExistingCount();
      } else {
        simpleSchedule.withMisfireHandlingInstructionFireNow();
      }

      Trigger trigger = TriggerBuilder.newTrigger()
          .withIdentity(refId, groupId)
          .startAt(Date.from(jobToCreate.getFireAt()))
          .withSchedule(simpleSchedule)
          .build();

      JobDetail detail = JobBuilder.newJob(OneTimeJob.class)
          .withIdentity(refId, groupId)
          .usingJobData(JobDataUtil.SCHEDULE_ID, jobToCreate.getId())
          .build();

      jobs.put(detail, Collections.singleton(trigger));
    });

    try {
      scheduler.scheduleJobs(jobs, false);
    } catch (Exception e) {
      throw new JobPersistenceException(e.getMessage(), SeverityLevel.HIGH);
    }

    return jobsToCreate;
  }

  // ---------------------------------------------------------------------------//

  @Override
  public Collection<CronJobDTO> saveAllCronJobs(Collection<CronJobDTO> jobsToCreate) {
    Map<JobDetail, Set<? extends Trigger>> jobs = new HashMap<>(jobsToCreate.size());

    jobsToCreate.forEach(jobToCreate -> {
      String refId = jobToCreate.getRefId();
      String groupId = jobToCreate.getGroupId();

      CronScheduleBuilder cronSchedule = CronScheduleBuilder.cronSchedule(jobToCreate.getExpression());

      if (jobToCreate.getIgnoreMisfire()) {
        cronSchedule.withMisfireHandlingInstructionDoNothing();
      } else {
        cronSchedule.withMisfireHandlingInstructionIgnoreMisfires();
      }

      Trigger trigger = TriggerBuilder.newTrigger()
          .withIdentity(refId, groupId)
          .startAt(Date.from(Instant.now()))
          .withSchedule(cronSchedule)
          .build();

      JobDetail detail = JobBuilder.newJob(CronJob.class)
          .withIdentity(refId, groupId)
          .usingJobData(JobDataUtil.SCHEDULE_ID, jobToCreate.getId())
          .build();

      jobs.put(detail, Collections.singleton(trigger));
    });

    try {
      scheduler.scheduleJobs(jobs, false);
    } catch (Exception e) {
      throw new JobPersistenceException(e.getMessage(), SeverityLevel.HIGH);
    }

    return jobsToCreate;
  }

  // ---------------------------------------------------------------------------//

  @Override
  public Collection<JobDTO> deleteAllJobs(Collection<JobDTO> jobsToDelete) {
    List<JobKey> jobKeys = new ArrayList<>(jobsToDelete.size());

    jobsToDelete.forEach(jobToDelete -> jobKeys.add(new JobKey(jobToDelete.getRefId(), jobToDelete.getGroupId())));

    try {
      scheduler.deleteJobs(jobKeys);
    } catch (Exception e) {
      throw new JobDeletionException(e.getMessage(), SeverityLevel.HIGH);
    }

    return jobsToDelete;
  }

}
