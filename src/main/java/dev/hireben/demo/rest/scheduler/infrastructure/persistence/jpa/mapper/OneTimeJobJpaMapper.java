package dev.hireben.demo.rest.scheduler.infrastructure.persistence.jpa.mapper;

import dev.hireben.demo.rest.scheduler.domain.entity.OneTimeJob;
import dev.hireben.demo.rest.scheduler.domain.model.Webhook;
import dev.hireben.demo.rest.scheduler.infrastructure.persistence.jpa.entity.OneTimeJobJpaEntity;
import dev.hireben.demo.rest.scheduler.infrastructure.persistence.jpa.entity.WebhookContentJpaEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class OneTimeJobJpaMapper {

  // ---------------------------------------------------------------------------//
  // Methods
  // ---------------------------------------------------------------------------//

  public OneTimeJobJpaEntity toEntity(OneTimeJob domain) {

    Webhook webhook = domain.getWebhook();

    WebhookContentJpaEntity webhookContent = WebhookContentJpaEntity.builder()
        .id(webhook.getId())
        .headers(webhook.getHeaders())
        .payload(webhook.getPayload())
        .build();

    OneTimeJobJpaEntity entity = OneTimeJobJpaEntity.builder()
        .id(domain.getId())
        .refId(domain.getRefId())
        .groupId(domain.getGroupId())
        .createdBy(domain.getCreatedBy())
        .createdAt(domain.getCreatedAt())
        .ignoreMisfire(domain.getIgnoreMisfire())
        .isActive(domain.getIsActive())
        .callbackUrl(webhook.getCallbackUrl())
        .webhookContent(webhookContent)
        .fireAt(domain.getFireAt())
        .build();

    return entity;
  }

  // ---------------------------------------------------------------------------//

  public OneTimeJob toDomain(OneTimeJobJpaEntity entity, boolean includeContent) {

    Webhook webhook = Webhook.builder()
        .callbackUrl(entity.getCallbackUrl())
        .build();

    if (includeContent) {
      WebhookContentJpaEntity webhookEntity = entity.getWebhookContent();
      webhook.setId(webhookEntity.getId());
      webhook.setHeaders(webhookEntity.getHeaders());
      webhook.setPayload(webhookEntity.getPayload());
    }

    OneTimeJob domain = OneTimeJob.builder()
        .id(entity.getId())
        .refId(entity.getRefId())
        .groupId(entity.getGroupId())
        .createdBy(entity.getCreatedBy())
        .createdAt(entity.getCreatedAt())
        .ignoreMisfire(entity.getIgnoreMisfire())
        .isActive(entity.getIsActive())
        .webhook(webhook)
        .fireAt(entity.getFireAt())
        .build();

    return domain;
  }

}
