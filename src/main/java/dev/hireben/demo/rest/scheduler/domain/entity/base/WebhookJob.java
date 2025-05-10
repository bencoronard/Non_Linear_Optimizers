package dev.hireben.demo.rest.scheduler.domain.entity.base;

import java.time.Instant;

import dev.hireben.demo.rest.scheduler.domain.entity.JobExecRecord;
import dev.hireben.demo.rest.scheduler.domain.entity.Webhook;
import dev.hireben.demo.rest.scheduler.domain.repository.JobExecRecordRepository;
import dev.hireben.demo.rest.scheduler.domain.repository.base.WebhookJobRepository;
import dev.hireben.demo.rest.scheduler.domain.service.WebhookDispatchService;
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

  public void execute(WebhookDispatchService dispatcher, WebhookJobRepository<? extends WebhookJob> jobRepository,
      JobExecRecordRepository recordRepository) {
    String result = "CANCELLED";

    try {
      if (!isActive) {
        return;
      }
      result = dispatcher.dispatch(webhook);
    } catch (Exception e) {
      result = e.getMessage();
    } finally {
      JobExecRecord record = JobExecRecord.builder()
          .jobId(id)
          .executedAt(Instant.now())
          .execResult(result)
          .build();

      recordRepository.save(record);
    }

  }

}
