package dev.hireben.demo.rest.scheduler.infrastructure.persistence.jpa.mapper;

import dev.hireben.demo.rest.scheduler.domain.entity.RecurringJob;
import dev.hireben.demo.rest.scheduler.infrastructure.persistence.jpa.entity.RecurringJobEntity;
import dev.hireben.demo.rest.scheduler.infrastructure.persistence.jpa.entity.WebhookContentEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class RecurringJobEntityMapper {

  // ---------------------------------------------------------------------------//
  // Methods
  // ---------------------------------------------------------------------------//

  public RecurringJobEntity toEntity(RecurringJob domain) {
    RecurringJobEntity entity = RecurringJobEntity.builder()
        .id(domain.getId())
        .refId(domain.getRefId())
        .groupId(domain.getGroupId())
        .createdBy(domain.getCreatedBy())
        .createdAt(domain.getCreatedAt())
        .ignoreMisfire(domain.getIgnoreMisfire())
        .isActive(domain.getIsActive())
        .callbackUrl(domain.getWebhook().getCallbackUrl())
        .cron(domain.getCron())
        .build();

    WebhookContentEntity webhookEntity = WebhookEntityMapper.toEntity(domain.getWebhook(), entity);
    entity.setWebhookData(webhookEntity);
    return entity;
  }

  // ---------------------------------------------------------------------------//

  public RecurringJob toDomain(RecurringJobEntity entity, boolean includeContent) {
    return RecurringJob.builder()
        .id(entity.getId())
        .refId(entity.getRefId())
        .groupId(entity.getGroupId())
        .createdBy(entity.getCreatedBy())
        .createdAt(entity.getCreatedAt())
        .ignoreMisfire(entity.getIgnoreMisfire())
        .isActive(entity.getIsActive())
        .webhook(WebhookEntityMapper.toDomain(entity.getWebhookData(), includeContent))
        .cron(entity.getCron())
        .build();
  }

}
