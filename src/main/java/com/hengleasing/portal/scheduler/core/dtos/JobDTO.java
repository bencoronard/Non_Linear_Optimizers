package com.hengleasing.portal.scheduler.core.dtos;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class JobDTO {

  // ---------------------------------------------------------------------------//
  // Fields
  // ---------------------------------------------------------------------------//

  private Long id;
  private String refId;
  private String groupId;
  private Boolean ignoreMisfire;

}
