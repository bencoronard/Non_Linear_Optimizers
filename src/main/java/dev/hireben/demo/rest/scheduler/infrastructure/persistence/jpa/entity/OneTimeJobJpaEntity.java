package dev.hireben.demo.rest.scheduler.infrastructure.persistence.jpa.entity;

import java.time.Instant;

import dev.hireben.demo.rest.scheduler.infrastructure.persistence.jpa.entity.base.WebhookJobJpaEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper = true)
@Table(name = "m_job_onetime", schema = "public")
public class OneTimeJobJpaEntity extends WebhookJobJpaEntity {

  // ---------------------------------------------------------------------------//
  // Fields
  // ---------------------------------------------------------------------------//

  @Column(name = "fire_at", nullable = false)
  private Instant fireAt;

}
