package dev.hireben.demo.rest.scheduler.infrastructure.persistence.jpa.mapper;

import dev.hireben.demo.rest.scheduler.domain.entity.RecurringJob;
import dev.hireben.demo.rest.scheduler.domain.model.Webhook;
import dev.hireben.demo.rest.scheduler.infrastructure.persistence.jpa.entity.RecurringJobJpaEntity;
import dev.hireben.demo.rest.scheduler.infrastructure.persistence.jpa.entity.WebhookContentJpaEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class RecurringJobJpaMapper {

  // ---------------------------------------------------------------------------//
  // Methods
  // ---------------------------------------------------------------------------//

  public RecurringJobJpaEntity toEntity(RecurringJob domain) {

    Webhook webhook = domain.getWebhook();

    WebhookContentJpaEntity webhookContent = WebhookContentJpaEntity.builder()
        .id(webhook.getId())
        .headers(webhook.getHeaders())
        .payload(webhook.getPayload())
        .build();

    RecurringJobJpaEntity entity = RecurringJobJpaEntity.builder()
        .id(domain.getId())
        .refId(domain.getRefId())
        .groupId(domain.getGroupId())
        .createdBy(domain.getCreatedBy())
        .createdAt(domain.getCreatedAt())
        .ignoreMisfire(domain.getIgnoreMisfire())
        .isActive(domain.getIsActive())
        .callbackUrl(webhook.getCallbackUrl())
        .webhookContent(webhookContent)
        .cron(domain.getCron())
        .build();

    return entity;
  }

  // ---------------------------------------------------------------------------//

  public RecurringJob toDomain(RecurringJobJpaEntity entity, boolean includeContent) {

    Webhook webhook = Webhook.builder()
        .callbackUrl(entity.getCallbackUrl())
        .build();

    if (includeContent) {
      WebhookContentJpaEntity webhookEntity = entity.getWebhookContent();
      webhook.setId(webhookEntity.getId());
      webhook.setHeaders(webhookEntity.getHeaders());
      webhook.setPayload(webhookEntity.getPayload());
    }

    RecurringJob domain = RecurringJob.builder()
        .id(entity.getId())
        .refId(entity.getRefId())
        .groupId(entity.getGroupId())
        .createdBy(entity.getCreatedBy())
        .createdAt(entity.getCreatedAt())
        .ignoreMisfire(entity.getIgnoreMisfire())
        .isActive(entity.getIsActive())
        .webhook(webhook)
        .cron(entity.getCron())
        .build();

    return domain;
  }

}
