package dev.hireben.demo.rest.scheduler.domain.entity;

import java.time.Instant;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JobExecutionRecord {

  // ---------------------------------------------------------------------------//
  // Fields
  // ---------------------------------------------------------------------------//

  private final Long id;
  private final Long jobId;
  private final Instant executedAt;
  private final String execResult;

}
