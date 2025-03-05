package com.hengleasing.portal.scheduler.core.controllers.implementations;

import java.util.Collection;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

import com.hengleasing.portal.scheduler.common.DefaultValue;
import com.hengleasing.portal.scheduler.common.dtos.GlobalResponseBody;
import com.hengleasing.portal.scheduler.core.controllers.ScheduleController;
import com.hengleasing.portal.scheduler.core.dtos.CreateCronScheduleDTO;
import com.hengleasing.portal.scheduler.core.dtos.CreateOneTimeScheduleDTO;
import com.hengleasing.portal.scheduler.core.dtos.ScheduleDTO;
import com.hengleasing.portal.scheduler.core.services.ScheduleService;
import com.hengleasing.portal.scheduler.utilities.annotations.NoNullElements;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Validated
@RestController
@RequiredArgsConstructor
public class ScheduleControllerImpl implements ScheduleController {

  // ---------------------------------------------------------------------------//
  // Dependencies
  // ---------------------------------------------------------------------------//

  private final ScheduleService scheduleService;

  // ---------------------------------------------------------------------------//
  // Methods
  // ---------------------------------------------------------------------------//

  @Override
  public ResponseEntity<GlobalResponseBody<Collection<ScheduleDTO>>> requestCreateOneTimeSchedules(
      @Valid @NoNullElements Collection<@Valid CreateOneTimeScheduleDTO> scheduleList, String originId) {

    Collection<ScheduleDTO> createdSchedules = scheduleService.createOneTimeSchedules(scheduleList, originId);

    GlobalResponseBody<Collection<ScheduleDTO>> responseBody = GlobalResponseBody.<Collection<ScheduleDTO>>builder()
        .respCode(DefaultValue.RESP_CODE_SUCCESS)
        .respMsg("scheduled one-time jobs")
        .data(createdSchedules)
        .build();

    return new ResponseEntity<>(responseBody, HttpStatus.OK);
  }

  // ---------------------------------------------------------------------------//

  @Override
  public ResponseEntity<GlobalResponseBody<Collection<ScheduleDTO>>> requestCreateCronSchedules(
      @Valid @NoNullElements Collection<@Valid CreateCronScheduleDTO> scheduleList, String originId) {

    Collection<ScheduleDTO> createdSchedules = scheduleService.createCronSchedules(scheduleList, originId);

    GlobalResponseBody<Collection<ScheduleDTO>> responseBody = GlobalResponseBody.<Collection<ScheduleDTO>>builder()
        .respCode(DefaultValue.RESP_CODE_SUCCESS)
        .respMsg("scheduled cron jobs")
        .data(createdSchedules)
        .build();

    return new ResponseEntity<>(responseBody, HttpStatus.OK);
  }

  // ---------------------------------------------------------------------------//

  @Override
  public ResponseEntity<GlobalResponseBody<ScheduleDTO>> requestDeleteOneTimeScheduleWithRefId(String refId,
      String originId) {

    ScheduleDTO deletedSchedule = scheduleService.deleteOneTimeScheduleWithRefId(refId, originId);

    GlobalResponseBody<ScheduleDTO> responseBody = GlobalResponseBody.<ScheduleDTO>builder()
        .respCode(DefaultValue.RESP_CODE_SUCCESS)
        .respMsg("descheduled one-time job")
        .data(deletedSchedule)
        .build();

    return new ResponseEntity<>(responseBody, HttpStatus.OK);
  }

  // ---------------------------------------------------------------------------//

  @Override
  public ResponseEntity<GlobalResponseBody<ScheduleDTO>> requestDeleteCronScheduleWithRefId(String refId,
      String originId) {

    ScheduleDTO deletedSchedule = scheduleService.deleteCronScheduleWithRefId(refId, originId);

    GlobalResponseBody<ScheduleDTO> responseBody = GlobalResponseBody.<ScheduleDTO>builder()
        .respCode(DefaultValue.RESP_CODE_SUCCESS)
        .respMsg("descheduled cron job")
        .data(deletedSchedule)
        .build();

    return new ResponseEntity<>(responseBody, HttpStatus.OK);
  }

  // ---------------------------------------------------------------------------//

  @Override
  public ResponseEntity<GlobalResponseBody<Collection<ScheduleDTO>>> requestDeleteAllOneTimeSchedulesWithGroupId(
      String groupId, String originId) {

    Collection<ScheduleDTO> deletedSchedules = scheduleService.deleteAllOneTimeSchedulesWithGroupId(groupId, originId);

    GlobalResponseBody<Collection<ScheduleDTO>> responseBody = GlobalResponseBody.<Collection<ScheduleDTO>>builder()
        .respCode(DefaultValue.RESP_CODE_SUCCESS)
        .respMsg("descheduled one-time jobs")
        .data(deletedSchedules)
        .build();

    return new ResponseEntity<>(responseBody, HttpStatus.OK);
  }

  // ---------------------------------------------------------------------------//

  @Override
  public ResponseEntity<GlobalResponseBody<Collection<ScheduleDTO>>> requestDeleteAllCronSchedulesWithGroupId(
      String groupId, String originId) {

    Collection<ScheduleDTO> deletedSchedules = scheduleService.deleteAllCronSchedulesWithGroupId(groupId, originId);

    GlobalResponseBody<Collection<ScheduleDTO>> responseBody = GlobalResponseBody.<Collection<ScheduleDTO>>builder()
        .respCode(DefaultValue.RESP_CODE_SUCCESS)
        .respMsg("descheduled cron jobs")
        .data(deletedSchedules)
        .build();

    return new ResponseEntity<>(responseBody, HttpStatus.OK);
  }

}
