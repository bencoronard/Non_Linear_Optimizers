package com.hengleasing.portal.scheduler.utilities.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.hengleasing.portal.scheduler.utilities.JsonValidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = JsonValidator.class)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidJson {
  String message() default "Invalid JSON format";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
