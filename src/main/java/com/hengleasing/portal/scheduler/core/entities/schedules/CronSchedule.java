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
@Table(name = "t_schedules_cron", schema = "scheduler", uniqueConstraints = @UniqueConstraint(columnNames = {
    "reference_id", "origin" }))
public class CronSchedule extends Schedule {

  // ---------------------------------------------------------------------------//
  // Fields
  // ---------------------------------------------------------------------------//

  @Column(name = "expression", updatable = false, nullable = false)
  private String expression;

  @Column(name = "is_active", nullable = false)
  private Boolean isActive;

  @Column(name = "last_executed_at")
  private Instant lastExecutedAt;

  @Enumerated(EnumType.STRING)
  @Column(name = "execution_status", length = 7, nullable = false)
  private CronExecutionStatus status;

  // ---------------------------------------------------------------------------//
  // Misc
  // ---------------------------------------------------------------------------//

  public enum CronExecutionStatus {
    ACTIVE, TERMINATED
  }

}
