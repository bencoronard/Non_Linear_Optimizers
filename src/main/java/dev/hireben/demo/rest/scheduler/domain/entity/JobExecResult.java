package dev.hireben.demo.rest.scheduler.domain.entity;

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
