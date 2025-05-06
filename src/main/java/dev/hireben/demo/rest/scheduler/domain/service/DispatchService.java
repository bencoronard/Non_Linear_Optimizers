package dev.hireben.demo.rest.scheduler.domain.service;

import dev.hireben.demo.rest.scheduler.domain.model.Webhook;

public interface DispatchService {

  String dispatch(Webhook webhook) throws Exception;

}
