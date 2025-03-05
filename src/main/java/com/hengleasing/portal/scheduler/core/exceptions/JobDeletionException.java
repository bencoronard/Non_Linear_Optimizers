package com.hengleasing.portal.scheduler.core.exceptions;

import com.hengleasing.portal.scheduler.exceptions.CustomException;
import com.hengleasing.portal.scheduler.exceptions.SeverityLevel;

public class JobDeletionException extends CustomException {

  // ---------------------------------------------------------------------------//
  // Fields
  // ---------------------------------------------------------------------------//

  private static final String ERROR_CODE = "2001";

  // ---------------------------------------------------------------------------//
  // Constructors
  // ---------------------------------------------------------------------------//

  public JobDeletionException(String message, SeverityLevel severity) {
    super(ERROR_CODE, message, severity);
  }

}
