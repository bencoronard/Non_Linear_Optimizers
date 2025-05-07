package dev.hireben.demo.rest.scheduler.domain.repository;

import java.util.Collection;

import dev.hireben.demo.rest.scheduler.domain.entity.RecurringJob;

public interface RecurringJobRepository {

  Collection<Long> saveAll(Collection<RecurringJob> jobs);

}
