package dev.hireben.demo.rest.scheduler.domain.entity;

import dev.hireben.demo.rest.scheduler.domain.entity.base.WebhookJob;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class RecurringJob extends WebhookJob {

  // ---------------------------------------------------------------------------//
  // Fields
  // ---------------------------------------------------------------------------//

  private String cron;

}
