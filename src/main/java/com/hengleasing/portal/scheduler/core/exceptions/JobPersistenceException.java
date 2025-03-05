package com.hengleasing.portal.scheduler.core.exceptions;

import com.hengleasing.portal.scheduler.exceptions.CustomException;
import com.hengleasing.portal.scheduler.exceptions.SeverityLevel;

public class JobPersistenceException extends CustomException {

  // ---------------------------------------------------------------------------//
  // Fields
  // ---------------------------------------------------------------------------//

  private static final String ERROR_CODE = "2002";

  // ---------------------------------------------------------------------------//
  // Constructors
  // ---------------------------------------------------------------------------//

  public JobPersistenceException(String message, SeverityLevel severity) {
    super(ERROR_CODE, message, severity);
  }

}
