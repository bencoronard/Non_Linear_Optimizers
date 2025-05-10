package dev.hireben.demo.rest.scheduler.domain.service;

import dev.hireben.demo.rest.scheduler.domain.entity.Webhook;

public interface WebhookDispatchService {

  String dispatch(Webhook webhook) throws Exception;

}
