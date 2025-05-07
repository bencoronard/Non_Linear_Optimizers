package dev.hireben.demo.rest.scheduler.domain.repository;

import java.util.Collection;

import dev.hireben.demo.rest.scheduler.domain.entity.OneTimeJob;

public interface OneTimeJobRepository {

  Collection<Long> saveAll(Collection<OneTimeJob> jobs);

}
