package dev.hireben.demo.rest.scheduler.infrastructure.persistence.jpa.mapper;

import dev.hireben.demo.rest.scheduler.domain.entity.RecurringJob;
import dev.hireben.demo.rest.scheduler.infrastructure.persistence.jpa.entity.RecurringJobJpaEntity;
import dev.hireben.demo.rest.scheduler.infrastructure.persistence.jpa.entity.WebhookContentJpaEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class RecurringJobJpaMapper {

  // ---------------------------------------------------------------------------//
  // Methods
  // ---------------------------------------------------------------------------//

  public RecurringJobJpaEntity toEntity(RecurringJob domain) {
    RecurringJobJpaEntity entity = RecurringJobJpaEntity.builder()
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

    WebhookContentJpaEntity webhookEntity = WebhookJpaMapper.toEntity(domain.getWebhook(), entity);
    entity.setWebhookData(webhookEntity);
    return entity;
  }

  // ---------------------------------------------------------------------------//

  public RecurringJob toDomain(RecurringJobJpaEntity entity, boolean includeContent) {
    return RecurringJob.builder()
        .id(entity.getId())
        .refId(entity.getRefId())
        .groupId(entity.getGroupId())
        .createdBy(entity.getCreatedBy())
        .createdAt(entity.getCreatedAt())
        .ignoreMisfire(entity.getIgnoreMisfire())
        .isActive(entity.getIsActive())
        .webhook(WebhookJpaMapper.toDomain(entity.getWebhookData(), includeContent))
        .cron(entity.getCron())
        .build();
  }

}
