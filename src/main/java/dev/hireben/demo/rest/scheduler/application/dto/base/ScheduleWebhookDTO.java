package dev.hireben.demo.rest.scheduler.application.dto.base;

import java.util.Map;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public abstract class ScheduleWebhookDTO {

  // ---------------------------------------------------------------------------//
  // Fields
  // ---------------------------------------------------------------------------//

  private final String refId;
  private final String groupId;
  private final String createdBy;
  private final Boolean ignoreMisfire;
  private final String callbackUrl;
  private final Map<String, String> headers;
  private final byte[] payload;

}
