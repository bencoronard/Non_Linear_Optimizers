package dev.hireben.demo.rest.scheduler.infrastructure.persistence.jpa.mapper;

import dev.hireben.demo.rest.scheduler.domain.entity.Webhook;
import dev.hireben.demo.rest.scheduler.infrastructure.persistence.jpa.entity.WebhookContentEntity;
import dev.hireben.demo.rest.scheduler.infrastructure.persistence.jpa.entity.base.WebhookJobEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class WebhookEntityMapper {

  // ---------------------------------------------------------------------------//
  // Methods
  // ---------------------------------------------------------------------------//

  public WebhookContentEntity toEntity(Webhook domain, WebhookJobEntity jobEntity) {
    return WebhookContentEntity.builder()
        .id(domain.getId())
        .webhookJob(jobEntity)
        .headers(domain.getHeaders())
        .payload(domain.getPayload())
        .build();
  }

  // ---------------------------------------------------------------------------//

  public Webhook toDomain(WebhookContentEntity entity, boolean includeContent) {
    return Webhook.builder()
        .id(entity.getId())
        .callbackUrl(entity.getWebhookJob().getCallbackUrl())
        .headers(includeContent ? entity.getHeaders() : null)
        .payload(includeContent ? entity.getPayload() : null)
        .build();
  }

}
