package com.hengleasing.portal.scheduler.core.exceptions;

import com.hengleasing.portal.scheduler.exceptions.CustomException;
import com.hengleasing.portal.scheduler.exceptions.SeverityLevel;

public class DataParsingException extends CustomException {

  // ---------------------------------------------------------------------------//
  // Fields
  // ---------------------------------------------------------------------------//

  private static final String ERROR_CODE = "1001";

  // ---------------------------------------------------------------------------//
  // Constructors
  // ---------------------------------------------------------------------------//

  public DataParsingException(String message, SeverityLevel severity) {
    super(ERROR_CODE, message, severity);
  }

}
