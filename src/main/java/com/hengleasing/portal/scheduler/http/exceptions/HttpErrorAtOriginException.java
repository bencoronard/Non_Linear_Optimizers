package com.hengleasing.portal.scheduler.http.exceptions;

import org.springframework.http.HttpStatusCode;

import com.hengleasing.portal.scheduler.exceptions.HttpResponseException;
import com.hengleasing.portal.scheduler.http.dtos.HttpClientResponse;

public class HttpErrorAtOriginException extends HttpResponseException {

  // ---------------------------------------------------------------------------//
  // Constructors
  // ---------------------------------------------------------------------------//

  public HttpErrorAtOriginException(HttpStatusCode code, String body, Throwable retryable) {
    super(new HttpClientResponse<>(code, body), retryable);
  }

}
