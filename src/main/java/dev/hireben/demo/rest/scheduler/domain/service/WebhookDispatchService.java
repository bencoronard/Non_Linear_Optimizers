package dev.hireben.demo.rest.scheduler.domain.service;

import dev.hireben.demo.rest.scheduler.domain.model.Webhook;

public interface WebhookDispatchService {

  String dispatch(Webhook webhook);

}
