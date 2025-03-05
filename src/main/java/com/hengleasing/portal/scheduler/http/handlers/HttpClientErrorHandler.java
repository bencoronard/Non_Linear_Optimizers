package com.hengleasing.portal.scheduler.http.handlers;

import java.io.IOException;
import java.util.Collection;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;

import com.hengleasing.portal.scheduler.exceptions.RetryableException;
import com.hengleasing.portal.scheduler.http.exceptions.HttpErrorAtDestinationException;
import com.hengleasing.portal.scheduler.http.exceptions.HttpErrorAtOriginException;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class HttpClientErrorHandler implements ResponseErrorHandler {

  // ---------------------------------------------------------------------------//
  // Fields
  // ---------------------------------------------------------------------------//

  private static final Collection<HttpStatusCode> RETRYABLE_STATUSES = Set.of(
      HttpStatus.REQUEST_TIMEOUT,
      HttpStatus.INTERNAL_SERVER_ERROR,
      HttpStatus.BAD_GATEWAY,
      HttpStatus.SERVICE_UNAVAILABLE,
      HttpStatus.GATEWAY_TIMEOUT);

  // ---------------------------------------------------------------------------//
  // Methods
  // ---------------------------------------------------------------------------//

  @Override
  public boolean hasError(@NonNull ClientHttpResponse response) throws IOException {
    return response.getStatusCode().isError();
  }

  // ---------------------------------------------------------------------------//

  @Override
  public void handleError(@NonNull ClientHttpResponse response) throws IOException {

    HttpStatusCode statusCode = response.getStatusCode();

    String body = new String(response.getBody().readAllBytes());

    Throwable retryable = null;
    if (RETRYABLE_STATUSES.contains(statusCode)) {
      retryable = new RetryableException();
    }

    throw statusCode.is4xxClientError() ? new HttpErrorAtOriginException(statusCode, body, retryable)
        : new HttpErrorAtDestinationException(statusCode, body, retryable);
  }

}
