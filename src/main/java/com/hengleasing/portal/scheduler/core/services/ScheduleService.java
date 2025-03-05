package com.hengleasing.portal.scheduler.core.services;

import java.util.Collection;

import com.hengleasing.portal.scheduler.core.dtos.CreateCronScheduleDTO;
import com.hengleasing.portal.scheduler.core.dtos.CreateOneTimeScheduleDTO;
import com.hengleasing.portal.scheduler.core.dtos.ScheduleDTO;

public interface ScheduleService {

  Collection<ScheduleDTO> createOneTimeSchedules(Collection<CreateOneTimeScheduleDTO> schedulesToCreate, String origin);

  Collection<ScheduleDTO> createCronSchedules(Collection<CreateCronScheduleDTO> schedulesToCreate, String origin);

  ScheduleDTO deleteOneTimeScheduleWithRefId(String refId, String origin);

  ScheduleDTO deleteCronScheduleWithRefId(String refId, String origin);

  Collection<ScheduleDTO> deleteAllOneTimeSchedulesWithGroupId(String groupId, String origin);

  Collection<ScheduleDTO> deleteAllCronSchedulesWithGroupId(String groupId, String origin);

}
