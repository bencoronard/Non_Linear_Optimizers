package dev.hireben.demo.rest.scheduler.application.dto;

import java.time.Instant;

import dev.hireben.demo.rest.scheduler.application.dto.base.ScheduleWebhookDTO;
import lombok.EqualsAndHashCode;
import lombok.Value;
import lombok.experimental.SuperBuilder;

@Value
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class ScheduleOneTimeJobDTO extends ScheduleWebhookDTO {

  // ---------------------------------------------------------------------------//
  // Fields
  // ---------------------------------------------------------------------------//

  Instant fireAt;

}
