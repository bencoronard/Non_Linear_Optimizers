package dev.hireben.demo.rest.scheduler.domain.entity;

import java.time.Instant;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class JobExecRecord {

  // ---------------------------------------------------------------------------//
  // Fields
  // ---------------------------------------------------------------------------//

  Long id;
  Long jobId;
  Instant executedAt;
  String execResult;

}
