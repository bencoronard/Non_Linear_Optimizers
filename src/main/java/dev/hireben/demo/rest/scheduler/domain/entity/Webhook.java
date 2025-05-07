package dev.hireben.demo.rest.scheduler.domain.entity;

import java.util.Map;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Webhook {

  // ---------------------------------------------------------------------------//
  // Fields
  // ---------------------------------------------------------------------------//

  private Long id;
  private String callbackUrl;
  private Map<String, String> headers;
  private byte[] payload; // nullable
}
