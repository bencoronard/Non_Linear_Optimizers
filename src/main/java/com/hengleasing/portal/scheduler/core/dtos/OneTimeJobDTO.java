package com.hengleasing.portal.scheduler.core.dtos;

import java.time.Instant;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class OneTimeJobDTO extends JobDTO {

  // ---------------------------------------------------------------------------//
  // Fields
  // ---------------------------------------------------------------------------//

  private Instant fireAt;

}
