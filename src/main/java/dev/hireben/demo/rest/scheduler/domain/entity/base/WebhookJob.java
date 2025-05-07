package dev.hireben.demo.rest.scheduler.domain.entity.base;

import java.time.Instant;

import dev.hireben.demo.rest.scheduler.domain.entity.JobExecRecord;
import dev.hireben.demo.rest.scheduler.domain.entity.Webhook;
import dev.hireben.demo.rest.scheduler.domain.repository.JobExecRecordRepository;
import dev.hireben.demo.rest.scheduler.domain.repository.WebhookRepository;
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

  public void execute(DispatchService dispatcher, WebhookRepository webhookRepo, JobExecRecordRepository jobRepo) {
    String result = "CANCELLED";

    try {
      if (!isActive) {
        return;
      }

      webhook.setPayload(webhookRepo.retrievePayload(webhook.getId()));

      result = dispatcher.dispatch(webhook);
    } catch (Exception e) {
      result = e.getMessage();
    } finally {
      JobExecRecord record = JobExecRecord.builder()
          .jobId(id)
          .executedAt(Instant.now())
          .execResult(result)
          .build();

      jobRepo.save(record);
    }

  }

}
