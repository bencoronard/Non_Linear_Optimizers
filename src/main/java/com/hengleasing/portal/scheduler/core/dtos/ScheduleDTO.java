package com.hengleasing.portal.scheduler.core.dtos;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@JsonPropertyOrder({ "id", "refId", "groupId" })
public class ScheduleDTO {

  // ---------------------------------------------------------------------------//
  // Fields
  // ---------------------------------------------------------------------------//

  private Long id;
  private String refId;
  private String groupId;

}
