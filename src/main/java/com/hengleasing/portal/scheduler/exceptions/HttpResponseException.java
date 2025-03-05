package com.hengleasing.portal.scheduler.exceptions;

import com.hengleasing.portal.scheduler.http.dtos.HttpClientResponse;

import lombok.Getter;

@Getter
public abstract class HttpResponseException extends RuntimeException {

  // ---------------------------------------------------------------------------//
  // Fields
  // ---------------------------------------------------------------------------//

  private final HttpClientResponse<String> response;

  // ---------------------------------------------------------------------------//
  // Constructors
  // ---------------------------------------------------------------------------//

  protected HttpResponseException(HttpClientResponse<String> response, Throwable retryable) {
    super(response.body(), retryable);
    this.response = response;
  }

}
