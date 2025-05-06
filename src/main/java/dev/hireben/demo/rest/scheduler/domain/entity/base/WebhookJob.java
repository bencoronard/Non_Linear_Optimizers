package dev.hireben.demo.rest.scheduler.domain.entity.base;

import java.time.Instant;

import dev.hireben.demo.rest.scheduler.domain.entity.JobExecutionRecord;
import dev.hireben.demo.rest.scheduler.domain.model.Webhook;
import dev.hireben.demo.rest.scheduler.domain.repository.JobExecutionRecordRepository;
import dev.hireben.demo.rest.scheduler.domain.service.DispatchService;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public abstract class WebhookJob {

  // ---------------------------------------------------------------------------//
  // Fields
  // ---------------------------------------------------------------------------//

  protected final Long id;
  protected final String origin;
  protected final String refId;
  protected final String groupId;
  protected final Instant createdAt;
  protected final Boolean ignoreMisfire;
  protected Boolean isActive;
  protected Webhook webhook;

  // ---------------------------------------------------------------------------//
  // Methods
  // ---------------------------------------------------------------------------//

  public void execute(DispatchService dispatcher, JobExecutionRecordRepository repository) {
    String result = "CANCELLED";

    try {
      if (!isActive) {
        return;
      }
      result = dispatcher.dispatch(webhook);
    } catch (Exception e) {
      result = e.getMessage();
    } finally {
      JobExecutionRecord record = JobExecutionRecord.builder()
          .jobId(id)
          .executedAt(Instant.now())
          .executionResult(result)
          .build();

      repository.save(record);
    }

  }

}
