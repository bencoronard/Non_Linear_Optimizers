package dev.hireben.demo.rest.scheduler.infrastructure.persistence.jpa.entity.base;

import java.time.Instant;

import dev.hireben.demo.rest.scheduler.infrastructure.persistence.jpa.entity.WebhookEntity;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@MappedSuperclass
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class WebhookJobEntity {

  // ---------------------------------------------------------------------------//
  // Fields
  // ---------------------------------------------------------------------------//

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "origin", updatable = false, nullable = false)
  private String origin;

  @Column(name = "reference_id", updatable = false, nullable = false)
  private String refId;

  @Column(name = "group_id", updatable = false, nullable = false)
  private String groupId;

  @Column(name = "created_at", updatable = false, nullable = false)
  private Instant createdAt;

  @Column(name = "ignore_misfire", nullable = false)
  private Boolean ignoreMisfire;

  @Column(name = "is_active", nullable = false)
  private Boolean isActive;

  @OneToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "webhook_id", nullable = false)
  private WebhookEntity webhook;

}
