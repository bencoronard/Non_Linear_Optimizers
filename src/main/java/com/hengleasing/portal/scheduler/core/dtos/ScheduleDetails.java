package com.hengleasing.portal.scheduler.core.dtos;

import com.hengleasing.portal.scheduler.utilities.annotations.ValidJson;

import jakarta.validation.constraints.NotBlank;

public record ScheduleDetails(
    String refId,
    String groupId,
    Boolean ignoreMisfire,
    @NotBlank(message = "Missing webhook's destination") String destination,
    @NotBlank(message = "Missing webhook's path") String path,
    @ValidJson(message = "Webhook's payload must be a JSON string") String payload) {
}
