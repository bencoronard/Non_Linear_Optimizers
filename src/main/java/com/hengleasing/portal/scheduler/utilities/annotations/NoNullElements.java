package com.hengleasing.portal.scheduler.utilities.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.hengleasing.portal.scheduler.utilities.NoNullElementsValidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = NoNullElementsValidator.class)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface NoNullElements {
  String message() default "Collection must not contain null elements";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
