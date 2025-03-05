package com.hengleasing.portal.scheduler.core.entities.schedules;

import java.time.Instant;

import com.hengleasing.portal.scheduler.core.entities.Schedule;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "t_schedules_onetime", schema = "scheduler", uniqueConstraints = @UniqueConstraint(columnNames = {
    "reference_id", "origin" }))
public class OneTimeSchedule extends Schedule {

  // ---------------------------------------------------------------------------//
  // Fields
  // ---------------------------------------------------------------------------//

  @Column(name = "fire_at", updatable = false, nullable = false)
  private Instant fireAt;

  @Column(name = "executed_at")
  private Instant executedAt;

  @Enumerated(EnumType.STRING)
  @Column(name = "execution_status", length = 9, nullable = false)
  private OneTimeExecutionStatus status;

  // ---------------------------------------------------------------------------//
  // Misc
  // ---------------------------------------------------------------------------//

  public enum OneTimeExecutionStatus {
    SCHEDULED, EXECUTED, CANCELLED
  }

}
