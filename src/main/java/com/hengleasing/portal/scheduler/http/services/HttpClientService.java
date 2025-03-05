package com.hengleasing.portal.scheduler.http.services;

import java.util.Map;

import org.springframework.retry.support.RetryTemplate;

import com.hengleasing.portal.scheduler.http.dtos.HttpClientResponse;

public interface HttpClientService {

  HttpClientResponse<String> post(String uri, String requestBody, Map<String, String> requestHeaders);

  HttpClientResponse<String> post(String uri, String requestBody, Map<String, String> requestHeaders,
      RetryTemplate withRetry);

}
