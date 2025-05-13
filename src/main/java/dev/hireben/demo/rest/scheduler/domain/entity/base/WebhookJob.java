package dev.hireben.demo.rest.scheduler.domain.entity.base;

import java.time.Instant;

import dev.hireben.demo.rest.scheduler.domain.entity.JobExecRecord;
import dev.hireben.demo.rest.scheduler.domain.model.Webhook;
import dev.hireben.demo.rest.scheduler.domain.repository.JobExecRecordRepository;
import dev.hireben.demo.rest.scheduler.domain.service.WebhookDispatchService;
import dev.hireben.demo.rest.scheduler.domain.utility.JobExecStatus;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public abstract class WebhookJob {

  // ---------------------------------------------------------------------------//
  // Fields
  // ---------------------------------------------------------------------------//

  private final Long id;
  private final String refId;
  private final String groupId;
  private final String createdBy;
  private final Instant createdAt;
  private final Boolean ignoreMisfire;
  private Boolean isActive;
  private Webhook webhook;

  // ---------------------------------------------------------------------------//
  // Methods
  // ---------------------------------------------------------------------------//

  public void execute(WebhookDispatchService dispatcher, JobExecRecordRepository recordRepository) {
    Instant now = Instant.now();
    String response;

    if (!isActive) {
      response = JobExecStatus.CANCELLED;
    }

    response = dispatcher.dispatch(webhook);

    JobExecRecord result = JobExecRecord.builder()
        .jobId(id)
        .executedAt(now)
        .execResult(response)
        .build();

    recordRepository.save(result);
  }

}
