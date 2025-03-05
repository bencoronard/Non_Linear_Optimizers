package com.hengleasing.portal.scheduler.registry.entities;

import lombok.Data;

@Data
public class ServiceRegistry {

  // ---------------------------------------------------------------------------//
  // Fields
  // ---------------------------------------------------------------------------//

  private String serviceId;
  private String baseUrl;
  private String secretKey;

}
