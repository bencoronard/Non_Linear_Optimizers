package com.hengleasing.portal.scheduler.core.repositories;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hengleasing.portal.scheduler.core.entities.schedules.CronSchedule;

@Repository
public interface CronScheduleRepository extends JpaRepository<CronSchedule, Long> {

  Optional<CronSchedule> findByRefIdAndOrigin(String refId, String origin);

  Collection<CronSchedule> findAllByGroupIdAndOrigin(String groupId, String origin);

}
