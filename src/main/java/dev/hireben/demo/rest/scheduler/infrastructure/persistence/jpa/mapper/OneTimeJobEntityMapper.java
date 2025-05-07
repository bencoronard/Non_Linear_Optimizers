package dev.hireben.demo.rest.scheduler.infrastructure.persistence.jpa.mapper;

import dev.hireben.demo.rest.scheduler.domain.entity.OneTimeJob;
import dev.hireben.demo.rest.scheduler.infrastructure.persistence.jpa.entity.OneTimeJobEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class OneTimeJobEntityMapper {

  // ---------------------------------------------------------------------------//
  // Methods
  // ---------------------------------------------------------------------------//

  public OneTimeJobEntity toEntity(OneTimeJob domain) {
    return OneTimeJobEntity.builder()
        .id(domain.getId())
        .origin(domain.getOrigin())
        .refId(domain.getRefId())
        .groupId(domain.getGroupId())
        .createdAt(domain.getCreatedAt())
        .ignoreMisfire(domain.getIgnoreMisfire())
        .isActive(domain.getIsActive())
        .webhook(WebhookEntityMapper.toEntity(domain.getWebhook()))
        .fireAt(domain.getFireAt())
        .build();
  }

  // ---------------------------------------------------------------------------//

  public OneTimeJob toDomain(OneTimeJobEntity entity, boolean includePayload) {
    return OneTimeJob.builder()
        .id(entity.getId())
        .origin(entity.getOrigin())
        .refId(entity.getRefId())
        .groupId(entity.getGroupId())
        .createdAt(entity.getCreatedAt())
        .ignoreMisfire(entity.getIgnoreMisfire())
        .isActive(entity.getIsActive())
        .webhook(WebhookEntityMapper.toDomain(entity.getWebhook(), includePayload))
        .fireAt(entity.getFireAt())
        .build();
  }

}
