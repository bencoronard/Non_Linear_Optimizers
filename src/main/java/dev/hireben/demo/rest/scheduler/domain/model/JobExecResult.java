package dev.hireben.demo.rest.scheduler.domain.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class JobExecResult {

  // ---------------------------------------------------------------------------//
  // Fields
  // ---------------------------------------------------------------------------//

  private final String code;
  private final String message;

}
