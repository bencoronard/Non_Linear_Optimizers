package dev.hireben.demo.rest.scheduler.infrastructure.persistence.jpa.entity;

import java.util.Map;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "t_webhook_data", schema = "public")
public class WebhookEntity {

  // ---------------------------------------------------------------------------//
  // Fields
  // ---------------------------------------------------------------------------//

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "url", nullable = false)
  private String callbackUrl;

  @JdbcTypeCode(SqlTypes.JSON)
  @Column(name = "headers", columnDefinition = "JSON")
  private Map<String, String> headers;

  @Lob
  @Basic(fetch = FetchType.LAZY)
  @Column(name = "payload", columnDefinition = "BLOB")
  private byte[] payload;

}
