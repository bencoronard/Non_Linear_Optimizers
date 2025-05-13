package dev.hireben.demo.rest.scheduler.infrastructure.persistence.quartz.repository;

import java.time.Instant;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.stereotype.Repository;

import dev.hireben.demo.rest.scheduler.domain.entity.OneTimeJob;
import dev.hireben.demo.rest.scheduler.domain.entity.RecurringJob;
import dev.hireben.demo.rest.scheduler.infrastructure.persistence.quartz.entity.OneTimeJobQuartzEntity;
import dev.hireben.demo.rest.scheduler.infrastructure.persistence.quartz.entity.RecurringJobQuartzEntity;
import dev.hireben.demo.rest.scheduler.infrastructure.persistence.quartz.utility.JobDataMapKey;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class WebhookJobQuartzRepository {

  // ---------------------------------------------------------------------------//
  // Fields
  // ---------------------------------------------------------------------------//

  private final Scheduler scheduler;

  // ---------------------------------------------------------------------------//
  // Methods
  // ---------------------------------------------------------------------------//

  public void scheduleAllOneTimeJobs(Collection<OneTimeJob> jobs) {

    Map<JobDetail, Set<? extends Trigger>> jobDataMap = new HashMap<>(jobs.size());

    jobs.forEach(job -> {
      String refId = job.getRefId();
      String groupId = job.getGroupId();

      SimpleScheduleBuilder simpleSchedule = SimpleScheduleBuilder.simpleSchedule().withRepeatCount(0);

      if (job.getIgnoreMisfire()) {
        simpleSchedule.withMisfireHandlingInstructionNextWithExistingCount();
      } else {
        simpleSchedule.withMisfireHandlingInstructionFireNow();
      }

      Trigger trigger = TriggerBuilder.newTrigger()
          .withIdentity(refId, groupId)
          .startAt(Date.from(job.getFireAt()))
          .withSchedule(simpleSchedule)
          .build();

      JobDetail detail = JobBuilder.newJob(OneTimeJobQuartzEntity.class)
          .withIdentity(refId, groupId)
          .usingJobData(JobDataMapKey.JOB_ID_KEY, job.getId())
          .build();

      jobDataMap.put(detail, Collections.singleton(trigger));
    });

    try {
      scheduler.scheduleJobs(jobDataMap, false);
    } catch (Exception e) {
      throw new RuntimeException(e.getMessage());
    }

  }

  // ---------------------------------------------------------------------------//

  public void scheduleAllRecurringJobs(Collection<RecurringJob> jobs) {

    Map<JobDetail, Set<? extends Trigger>> jobDataMap = new HashMap<>(jobs.size());

    jobs.forEach(job -> {
      String refId = job.getRefId();
      String groupId = job.getGroupId();

      CronScheduleBuilder cronSchedule = CronScheduleBuilder.cronSchedule(job.getCron());

      if (job.getIgnoreMisfire()) {
        cronSchedule.withMisfireHandlingInstructionDoNothing();
      } else {
        cronSchedule.withMisfireHandlingInstructionIgnoreMisfires();
      }

      Trigger trigger = TriggerBuilder.newTrigger()
          .withIdentity(refId, groupId)
          .startAt(Date.from(Instant.now()))
          .withSchedule(cronSchedule)
          .build();

      JobDetail detail = JobBuilder.newJob(RecurringJobQuartzEntity.class)
          .withIdentity(refId, groupId)
          .usingJobData(JobDataMapKey.JOB_ID_KEY, job.getId())
          .build();

      jobDataMap.put(detail, Collections.singleton(trigger));
    });

    try {
      scheduler.scheduleJobs(jobDataMap, false);
    } catch (Exception e) {
      throw new RuntimeException(e.getMessage());
    }
  }

}
