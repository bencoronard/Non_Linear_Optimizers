package com.hengleasing.portal.scheduler.core.repositories;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hengleasing.portal.scheduler.core.entities.schedules.OneTimeSchedule;

@Repository
public interface OneTimeScheduleRepository extends JpaRepository<OneTimeSchedule, Long> {

  Optional<OneTimeSchedule> findByRefIdAndOrigin(String refId, String origin);

  Collection<OneTimeSchedule> findAllByGroupIdAndOrigin(String groupId, String origin);

}
