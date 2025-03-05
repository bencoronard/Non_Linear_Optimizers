package com.hengleasing.portal.scheduler.utilities.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.hengleasing.portal.scheduler.utilities.FutureTimeValidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = FutureTimeValidator.class)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface InFuture {
  String message() default "Time must be in the future";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
