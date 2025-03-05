package com.hengleasing.portal.scheduler.utilities;

import java.util.Collection;
import java.util.Objects;

import com.hengleasing.portal.scheduler.utilities.annotations.NoNullElements;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NoNullElementsValidator implements ConstraintValidator<NoNullElements, Collection<?>> {

  @Override
  public boolean isValid(Collection<?> value, ConstraintValidatorContext context) {

    if (value == null) {
      return true;
    }

    return value.stream().noneMatch(Objects::isNull);
  }

}
