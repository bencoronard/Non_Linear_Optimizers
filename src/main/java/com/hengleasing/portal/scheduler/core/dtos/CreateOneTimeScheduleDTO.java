package com.hengleasing.portal.scheduler.core.dtos;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record CreateOneTimeScheduleDTO(
    @Valid ScheduleDetails details,
    @NotNull(message = "Missing webhook's fire time") Long fireAt) {
}
