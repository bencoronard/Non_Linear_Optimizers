package com.hengleasing.portal.scheduler.registry.services;

import java.util.Optional;

import com.hengleasing.portal.scheduler.registry.entities.ServiceRegistry;

public interface ServiceRegistryService {

  boolean checkRegisteredWithServiceId(String id);

  Optional<ServiceRegistry> retrieveServiceWithServiceId(String id);

}
