package dev.hireben.demo.rest.scheduler.infrastructure.persistence.jpa.mapper;

import dev.hireben.demo.rest.scheduler.domain.entity.Webhook;
import dev.hireben.demo.rest.scheduler.infrastructure.persistence.jpa.entity.WebhookEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class WebhookEntityMapper {

  // ---------------------------------------------------------------------------//
  // Methods
  // ---------------------------------------------------------------------------//

  public WebhookEntity toEntity(Webhook domain) {
    return WebhookEntity.builder()
        .id(domain.getId())
        .callbackUrl(domain.getCallbackUrl())
        .headers(domain.getHeaders())
        .payload(domain.getPayload())
        .build();
  }

  // ---------------------------------------------------------------------------//

  public Webhook toDomain(WebhookEntity entity, boolean includePayload) {
    return Webhook.builder()
        .id(entity.getId())
        .callbackUrl(entity.getCallbackUrl())
        .headers(entity.getHeaders())
        .payload(includePayload ? entity.getPayload() : null)
        .build();
  }

}
