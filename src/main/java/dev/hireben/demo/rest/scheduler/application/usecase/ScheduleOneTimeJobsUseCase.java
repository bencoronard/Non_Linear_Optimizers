package dev.hireben.demo.rest.scheduler.application.usecase;

import java.time.Instant;
import java.util.Collection;
import java.util.stream.Collectors;

import dev.hireben.demo.rest.scheduler.application.dto.ScheduleOneTimeJobDTO;
import dev.hireben.demo.rest.scheduler.domain.entity.OneTimeJob;
import dev.hireben.demo.rest.scheduler.domain.entity.Webhook;
import dev.hireben.demo.rest.scheduler.domain.repository.OneTimeJobRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ScheduleOneTimeJobsUseCase {

  // ---------------------------------------------------------------------------//
  // Fields
  // ---------------------------------------------------------------------------//

  private final OneTimeJobRepository repository;

  // ---------------------------------------------------------------------------//
  // Methods
  // ---------------------------------------------------------------------------//

  public void execute(Collection<ScheduleOneTimeJobDTO> dtos) {

    Collection<OneTimeJob> entities = dtos.stream()
        .map(dto -> OneTimeJob.builder()
            .refId(dto.getRefId())
            .groupId(dto.getGroupId())
            .createdBy(dto.getCreatedBy())
            .createdAt(Instant.now())
            .ignoreMisfire(dto.getIgnoreMisfire())
            .fireAt(dto.getFireAt())
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
