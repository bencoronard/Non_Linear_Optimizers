package com.hengleasing.portal.scheduler.core.entities;

import java.time.Instant;
import java.time.ZoneId;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.lang.NonNull;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.hengleasing.portal.scheduler.common.DefaultValue;
import com.hengleasing.portal.scheduler.core.utilities.JobDataUtil;
import com.hengleasing.portal.scheduler.exceptions.CustomException;
import com.hengleasing.portal.scheduler.exceptions.SeverityLevel;
import com.hengleasing.portal.scheduler.utilities.ExceptionUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class QuartzJob extends QuartzJobBean {

  // ---------------------------------------------------------------------------//
  // Methods
  // ---------------------------------------------------------------------------//

  protected abstract void executeJobLogic(JobExecutionContext context) throws Exception;

  // ---------------------------------------------------------------------------//

  @Override
  protected void executeInternal(@NonNull JobExecutionContext context) throws JobExecutionException {

    Long id = null;

    try {

      id = context.getMergedJobDataMap().getLongValue(JobDataUtil.SCHEDULE_ID);

      executeJobLogic(context);

    } catch (Exception e) {

      String errorCode = DefaultValue.RESP_CODE_UNKNOWN;
      SeverityLevel severity = SeverityLevel.MEDIUM;

      if (e instanceof CustomException ex) {
        errorCode = ex.getErrorCode();
        severity = ex.getSeverity();
      }

      String debugMsg = ExceptionUtil.formatDebugString(e.getClass(), errorCode, severity, e.getMessage());

      String identifier = id != null ? id.toString() : Instant.now().atZone(ZoneId.systemDefault()).toString();

      log.error(ExceptionUtil.formatTraceLog(identifier, debugMsg));
    }

  }

}
