package dev.hireben.demo.rest.scheduler.infrastructure.persistence.jpa.mapper;

import dev.hireben.demo.rest.scheduler.domain.entity.OneTimeJob;
import dev.hireben.demo.rest.scheduler.infrastructure.persistence.jpa.entity.OneTimeJobJpaEntity;
import dev.hireben.demo.rest.scheduler.infrastructure.persistence.jpa.entity.WebhookContentJpaEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class OneTimeJobJpaMapper {

  // ---------------------------------------------------------------------------//
  // Methods
  // ---------------------------------------------------------------------------//

  public OneTimeJobJpaEntity toEntity(OneTimeJob domain) {
    OneTimeJobJpaEntity entity = OneTimeJobJpaEntity.builder()
        .id(domain.getId())
        .refId(domain.getRefId())
        .groupId(domain.getGroupId())
        .createdBy(domain.getCreatedBy())
        .createdAt(domain.getCreatedAt())
        .ignoreMisfire(domain.getIgnoreMisfire())
        .isActive(domain.getIsActive())
        .callbackUrl(domain.getWebhook().getCallbackUrl())
        .fireAt(domain.getFireAt())
        .build();

    WebhookContentJpaEntity webhookEntity = WebhookJpaMapper.toEntity(domain.getWebhook(), entity);
    entity.setWebhookData(webhookEntity);
    return entity;
  }

  // ---------------------------------------------------------------------------//

  public OneTimeJob toDomain(OneTimeJobJpaEntity entity, boolean includeContent) {
    return OneTimeJob.builder()
        .id(entity.getId())
        .refId(entity.getRefId())
        .groupId(entity.getGroupId())
        .createdBy(entity.getCreatedBy())
        .createdAt(entity.getCreatedAt())
        .ignoreMisfire(entity.getIgnoreMisfire())
        .isActive(entity.getIsActive())
        .webhook(WebhookJpaMapper.toDomain(entity.getWebhookData(), includeContent))
        .fireAt(entity.getFireAt())
        .build();
  }

}
