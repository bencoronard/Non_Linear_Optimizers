package dev.hireben.demo.rest.scheduler.infrastructure.persistence.jpa.mapper;

import dev.hireben.demo.rest.scheduler.domain.entity.RecurringJob;
import dev.hireben.demo.rest.scheduler.infrastructure.persistence.jpa.entity.RecurringJobEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class RecurringJobEntityMapper {

  // ---------------------------------------------------------------------------//
  // Methods
  // ---------------------------------------------------------------------------//

  public RecurringJobEntity toEntity(RecurringJob domain) {
    return RecurringJobEntity.builder()
        .id(domain.getId())
        .origin(domain.getOrigin())
        .refId(domain.getRefId())
        .groupId(domain.getGroupId())
        .createdAt(domain.getCreatedAt())
        .ignoreMisfire(domain.getIgnoreMisfire())
        .isActive(domain.getIsActive())
        .webhook(WebhookEntityMapper.toEntity(domain.getWebhook()))
        .cron(domain.getCron())
        .build();
  }

  // ---------------------------------------------------------------------------//

  public RecurringJob toDomain(RecurringJobEntity entity, boolean includePayload) {
    return RecurringJob.builder()
        .id(entity.getId())
        .origin(entity.getOrigin())
        .refId(entity.getRefId())
        .groupId(entity.getGroupId())
        .createdAt(entity.getCreatedAt())
        .ignoreMisfire(entity.getIgnoreMisfire())
        .isActive(entity.getIsActive())
        .webhook(WebhookEntityMapper.toDomain(entity.getWebhook(), includePayload))
        .cron(entity.getCron())
        .build();
  }

}
