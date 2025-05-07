package dev.hireben.demo.rest.scheduler.domain.repository;

public interface WebhookRepository {

  byte[] retrievePayload(Long id);

}
