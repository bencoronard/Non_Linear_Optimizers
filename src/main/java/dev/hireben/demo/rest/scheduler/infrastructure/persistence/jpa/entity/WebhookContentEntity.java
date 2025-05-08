package dev.hireben.demo.rest.scheduler.infrastructure.persistence.jpa.entity;

import java.util.Map;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import dev.hireben.demo.rest.scheduler.infrastructure.persistence.jpa.entity.base.WebhookJobEntity;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
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
@Table(name = "m_webhook_content", schema = "public")
public class WebhookContentEntity {

  // ---------------------------------------------------------------------------//
  // Fields
  // ---------------------------------------------------------------------------//

  @Id
  private Long id;

  @OneToOne(fetch = FetchType.LAZY)
  @MapsId
  @JoinColumn(name = "id")
  private WebhookJobEntity webhookJob;

  @JdbcTypeCode(SqlTypes.JSON)
  @Column(name = "headers", columnDefinition = "JSON")
  private Map<String, String> headers;

  @Lob
  @Basic(fetch = FetchType.LAZY)
  @Column(name = "payload", columnDefinition = "BLOB")
  private byte[] payload;

}
