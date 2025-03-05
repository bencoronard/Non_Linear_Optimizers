package com.hengleasing.portal.scheduler.core.exceptions;

import com.hengleasing.portal.scheduler.exceptions.CustomException;
import com.hengleasing.portal.scheduler.exceptions.SeverityLevel;

public class ScheduleNotFoundException extends CustomException {

  // ---------------------------------------------------------------------------//
  // Fields
  // ---------------------------------------------------------------------------//

  private static final String ERROR_CODE = "3001";

  // ---------------------------------------------------------------------------//
  // Constructors
  // ---------------------------------------------------------------------------//

  public ScheduleNotFoundException(String message, SeverityLevel severity) {
    super(ERROR_CODE, message, severity);
  }

}
