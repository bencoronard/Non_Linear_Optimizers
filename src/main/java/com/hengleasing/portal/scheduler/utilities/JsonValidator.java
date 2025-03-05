package com.hengleasing.portal.scheduler.utilities;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hengleasing.portal.scheduler.utilities.annotations.ValidJson;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JsonValidator implements ConstraintValidator<ValidJson, String> {

  // ---------------------------------------------------------------------------//
  // Dependencies
  // ---------------------------------------------------------------------------//

  private final ObjectMapper objectMapper;

  // ---------------------------------------------------------------------------//
  // Methods
  // ---------------------------------------------------------------------------//

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {

    if (value == null) {
      return true;
    }

    if (value.isBlank()) {
      return false;
    }

    try {
      JsonNode jsonNode = objectMapper.readTree(value);
      return jsonNode.isObject() || jsonNode.isArray();
    } catch (Exception e) {
      return false;
    }

  }

}
