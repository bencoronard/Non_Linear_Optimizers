package com.hengleasing.portal.scheduler.core.controllers;

import java.util.Collection;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hengleasing.portal.scheduler.common.dtos.GlobalResponseBody;
import com.hengleasing.portal.scheduler.context.RequestAttributeKey;
import com.hengleasing.portal.scheduler.core.dtos.CreateCronScheduleDTO;
import com.hengleasing.portal.scheduler.core.dtos.CreateOneTimeScheduleDTO;
import com.hengleasing.portal.scheduler.core.dtos.ScheduleDTO;
import com.hengleasing.portal.scheduler.utilities.annotations.NoNullElements;

import jakarta.validation.Valid;

@RequestMapping("/scheduler/api/v1")
public interface ScheduleController {

  @PostMapping("/jobs")
  ResponseEntity<GlobalResponseBody<Collection<ScheduleDTO>>> requestCreateOneTimeSchedules(
      @Valid @NoNullElements @RequestBody Collection<@Valid CreateOneTimeScheduleDTO> scheduleList,
      @RequestAttribute(RequestAttributeKey.ORIGIN_ID) String originId);

  @PostMapping("/jobs/cron")
  ResponseEntity<GlobalResponseBody<Collection<ScheduleDTO>>> requestCreateCronSchedules(
      @Valid @NoNullElements @RequestBody Collection<@Valid CreateCronScheduleDTO> scheduleList,
      @RequestAttribute(RequestAttributeKey.ORIGIN_ID) String originId);

  @DeleteMapping("/jobs/ref/{id}")
  ResponseEntity<GlobalResponseBody<ScheduleDTO>> requestDeleteOneTimeScheduleWithRefId(
      @PathVariable(name = "id") String refId,
      @RequestAttribute(RequestAttributeKey.ORIGIN_ID) String originId);

  @DeleteMapping("/jobs/ref/{id}/cron")
  ResponseEntity<GlobalResponseBody<ScheduleDTO>> requestDeleteCronScheduleWithRefId(
      @PathVariable(name = "id") String refId,
      @RequestAttribute(RequestAttributeKey.ORIGIN_ID) String originId);

  @DeleteMapping("/jobs/group/{id}")
  ResponseEntity<GlobalResponseBody<Collection<ScheduleDTO>>> requestDeleteAllOneTimeSchedulesWithGroupId(
      @PathVariable(name = "id") String groupId,
      @RequestAttribute(RequestAttributeKey.ORIGIN_ID) String originId);

  @DeleteMapping("/jobs/group/{id}/cron")
  ResponseEntity<GlobalResponseBody<Collection<ScheduleDTO>>> requestDeleteAllCronSchedulesWithGroupId(
      @PathVariable(name = "id") String groupId,
      @RequestAttribute(RequestAttributeKey.ORIGIN_ID) String originId);

}
