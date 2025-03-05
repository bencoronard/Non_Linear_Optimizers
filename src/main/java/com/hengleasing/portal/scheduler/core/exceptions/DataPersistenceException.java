package com.hengleasing.portal.scheduler.core.exceptions;

import com.hengleasing.portal.scheduler.exceptions.CustomException;
import com.hengleasing.portal.scheduler.exceptions.SeverityLevel;

public class DataPersistenceException extends CustomException {

  // ---------------------------------------------------------------------------//
  // Fields
  // ---------------------------------------------------------------------------//

  private static final String ERROR_CODE = "1002";

  // ---------------------------------------------------------------------------//
  // Constructors
  // ---------------------------------------------------------------------------//

  public DataPersistenceException(String message, SeverityLevel severity) {
    super(ERROR_CODE, message, severity);
  }

}
