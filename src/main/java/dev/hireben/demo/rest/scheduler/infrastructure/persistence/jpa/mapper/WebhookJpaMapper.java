package dev.hireben.demo.rest.scheduler.infrastructure.persistence.jpa.mapper;

import dev.hireben.demo.rest.scheduler.domain.model.Webhook;
import dev.hireben.demo.rest.scheduler.infrastructure.persistence.jpa.entity.WebhookContentJpaEntity;
import dev.hireben.demo.rest.scheduler.infrastructure.persistence.jpa.entity.base.WebhookJobJpaEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class WebhookJpaMapper {

  // ---------------------------------------------------------------------------//
  // Methods
  // ---------------------------------------------------------------------------//

  public WebhookContentJpaEntity toEntity(Webhook domain, WebhookJobJpaEntity jobEntity) {
    return WebhookContentJpaEntity.builder()
        .webhookJob(jobEntity)
        .headers(domain.getHeaders())
        .payload(domain.getPayload())
        .build();
  }

  // ---------------------------------------------------------------------------//

  public Webhook toDomain(WebhookContentJpaEntity entity, boolean includeContent) {
    return Webhook.builder()
        .callbackUrl(entity.getWebhookJob().getCallbackUrl())
        .headers(includeContent ? entity.getHeaders() : null)
        .payload(includeContent ? entity.getPayload() : null)
        .build();
  }

}
