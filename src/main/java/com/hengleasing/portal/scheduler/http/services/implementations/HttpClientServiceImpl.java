package com.hengleasing.portal.scheduler.http.services.implementations;

import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClient.RequestBodySpec;

import com.hengleasing.portal.scheduler.http.dtos.HttpClientResponse;
import com.hengleasing.portal.scheduler.http.services.HttpClientService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HttpClientServiceImpl implements HttpClientService {

  // ---------------------------------------------------------------------------//
  // Dependencies
  // ---------------------------------------------------------------------------//

  private final RestClient restClient;

  // ---------------------------------------------------------------------------//
  // Methods
  // ---------------------------------------------------------------------------//

  @Override
  public HttpClientResponse<String> post(String uri, String requestBody, Map<String, String> requestHeaders) {

    RequestBodySpec request = restClient.post().uri(uri);

    if (requestHeaders != null) {
      requestHeaders.forEach(request::header);
    }

    if (requestBody != null) {
      request.contentType(MediaType.APPLICATION_JSON).body(requestBody);
    }

    ResponseEntity<String> response = request.retrieve().toEntity(String.class);

    return new HttpClientResponse<>(response.getStatusCode(), response.getBody());
  }

  // ---------------------------------------------------------------------------//

  @Override
  public HttpClientResponse<String> post(String uri, String requestBody, Map<String, String> requestHeaders,
      RetryTemplate withRetry) {
    return (HttpClientResponse<String>) withRetry.execute((_) -> post(uri, requestBody, requestHeaders));
  }

}
