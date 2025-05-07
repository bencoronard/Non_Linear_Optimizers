package dev.hireben.demo.rest.scheduler.domain.repository;

import java.util.Collection;

import dev.hireben.demo.rest.scheduler.domain.entity.Webhook;

public interface WebhookRepository {

  Collection<Long> saveAll(Collection<Webhook> webhooks);

  byte[] retrievePayload(Long id);

}
