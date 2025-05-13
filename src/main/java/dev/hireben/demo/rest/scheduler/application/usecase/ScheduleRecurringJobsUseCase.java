package dev.hireben.demo.rest.scheduler.application.usecase;

import java.time.Instant;
import java.util.Collection;
import java.util.stream.Collectors;

import dev.hireben.demo.rest.scheduler.application.dto.ScheduleRecurringJobDTO;
import dev.hireben.demo.rest.scheduler.domain.entity.RecurringJob;
import dev.hireben.demo.rest.scheduler.domain.model.Webhook;
import dev.hireben.demo.rest.scheduler.domain.repository.RecurringJobRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ScheduleRecurringJobsUseCase {

  // ---------------------------------------------------------------------------//
  // Fields
  // ---------------------------------------------------------------------------//

  private final RecurringJobRepository repository;

  // ---------------------------------------------------------------------------//
  // Methods
  // ---------------------------------------------------------------------------//

  public void execute(Collection<ScheduleRecurringJobDTO> dtos) {

    Collection<RecurringJob> entities = dtos.stream()
        .map(dto -> RecurringJob.builder()
            .refId(dto.getRefId())
            .groupId(dto.getGroupId())
            .createdBy(dto.getCreatedBy())
            .createdAt(Instant.now())
            .ignoreMisfire(dto.getIgnoreMisfire())
            .cron(dto.getCron())
            .webhook(Webhook.builder()
                .callbackUrl(dto.getCallbackUrl())
                .headers(dto.getHeaders())
                .payload(dto.getPayload())
                .build())
            .build())
        .collect(Collectors.toList());

    repository.saveAll(entities);
  }

}
