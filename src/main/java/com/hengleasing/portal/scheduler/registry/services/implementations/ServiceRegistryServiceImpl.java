package com.hengleasing.portal.scheduler.registry.services.implementations;

import java.util.List;
import java.util.Optional;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import com.hengleasing.portal.scheduler.registry.entities.ServiceRegistry;
import com.hengleasing.portal.scheduler.registry.services.ServiceRegistryService;

import lombok.Setter;

@Service
@Setter
@ConfigurationProperties(prefix = "registry")
public class ServiceRegistryServiceImpl implements ServiceRegistryService {

  // ---------------------------------------------------------------------------//
  // Fields
  // ---------------------------------------------------------------------------//

  private List<ServiceRegistry> services;

  // ---------------------------------------------------------------------------//
  // Methods
  // ---------------------------------------------------------------------------//

  @Override
  public boolean checkRegisteredWithServiceId(String id) {
    for (ServiceRegistry service : services) {
      if (service.getServiceId().equals(id)) {
        return true;
      }
    }
    return false;
  }

  // ---------------------------------------------------------------------------//

  @Override
  public Optional<ServiceRegistry> retrieveServiceWithServiceId(String id) {
    for (ServiceRegistry service : services) {
      if (service.getServiceId().equals(id)) {
        return Optional.of(service);
      }
    }
    return Optional.empty();
  }

}
