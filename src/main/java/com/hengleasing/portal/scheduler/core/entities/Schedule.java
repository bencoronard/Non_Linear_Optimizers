package com.hengleasing.portal.scheduler.core.entities;

import java.time.Instant;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@MappedSuperclass
@Data
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class Schedule {

  // ---------------------------------------------------------------------------//
  // Fields
  // ---------------------------------------------------------------------------//

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", updatable = false, nullable = false)
  private Long id;

  @Column(name = "origin", updatable = false, nullable = false)
  private String origin;

  @Column(name = "reference_id")
  private String refId;

  @Column(name = "group_id")
  private String groupId;

  @Column(name = "ignore_misfire", nullable = false)
  private Boolean ignoreMisfire;

  @Column(name = "created_at", updatable = false, nullable = false)
  private Instant createdAt;

  @Column(name = "destination", nullable = false)
  private String destination;

  @Column(name = "path", nullable = false)
  private String path;

  @JdbcTypeCode(SqlTypes.JSON)
  @Column(name = "payload", columnDefinition = "JSON")
  private String payload;

  @JdbcTypeCode(SqlTypes.JSON)
  @Column(name = "execution_note", columnDefinition = "JSON")
  private ExecutionResult executionNote;

}
