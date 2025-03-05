package com.hengleasing.portal.scheduler.utilities;

import java.time.Instant;

import com.hengleasing.portal.scheduler.utilities.annotations.InFuture;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class FutureTimeValidator implements ConstraintValidator<InFuture, Long> {

  // ---------------------------------------------------------------------------//
  // Methods
  // ---------------------------------------------------------------------------//

  @Override
  public boolean isValid(Long value, ConstraintValidatorContext context) {

    if (value == null) {
      return true;
    }

    return Instant.ofEpochSecond(value).isAfter(Instant.now());
  }

}
