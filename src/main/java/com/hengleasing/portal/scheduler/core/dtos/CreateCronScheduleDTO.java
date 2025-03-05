package com.hengleasing.portal.scheduler.core.dtos;

import com.hengleasing.portal.scheduler.utilities.annotations.ValidCron;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record CreateCronScheduleDTO(
    @Valid ScheduleDetails details,
    @NotNull(message = "Missing CRON expression") @ValidCron(message = "Expression must be a CRON expression") String expression) {
}
