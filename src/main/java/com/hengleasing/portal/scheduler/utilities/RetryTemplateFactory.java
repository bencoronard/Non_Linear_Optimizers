package com.hengleasing.portal.scheduler.utilities;

import java.util.List;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.retry.support.RetryTemplateBuilder;
import com.hengleasing.portal.scheduler.exceptions.RetryableException;

public class RetryTemplateFactory {

  // ---------------------------------------------------------------------------//
  // Fields
  // ---------------------------------------------------------------------------//

  private static final List<Class<? extends Throwable>> RETRYABLE_EXCEPTIONS = List.of(RetryableException.class);

  // ---------------------------------------------------------------------------//
  // Methods
  // ---------------------------------------------------------------------------//

  public static RetryTemplate withFixedBackOff(int maxAttempts, long backoffMillis) {
    return new RetryTemplateBuilder()
        .maxAttempts(maxAttempts)
        .fixedBackoff(backoffMillis)
        .retryOn(RETRYABLE_EXCEPTIONS)
        .traversingCauses()
        .build();
  }

  // ---------------------------------------------------------------------------//

  public static RetryTemplate withExponentialBackOff(int maxAttempts, long initInterval, double multiplier,
      long maxInterval) {
    return new RetryTemplateBuilder()
        .maxAttempts(maxAttempts)
        .exponentialBackoff(initInterval, multiplier, maxInterval)
        .retryOn(RETRYABLE_EXCEPTIONS)
        .traversingCauses()
        .build();
  }

}
