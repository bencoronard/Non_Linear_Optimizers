package com.hengleasing.portal.scheduler.utilities;

import org.quartz.CronExpression;

import com.hengleasing.portal.scheduler.utilities.annotations.ValidCron;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CronValidator implements ConstraintValidator<ValidCron, String> {

  // ---------------------------------------------------------------------------//
  // Methods
  // ---------------------------------------------------------------------------//

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {

    if (value == null) {
      return true;
    }

    return CronExpression.isValidExpression(value);
  }

}
