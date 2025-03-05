package com.hengleasing.portal.scheduler.core.entities;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ExecutionResult {

  // ---------------------------------------------------------------------------//
  // Fields
  // ---------------------------------------------------------------------------//

  private final String code;
  private final String message;

}
