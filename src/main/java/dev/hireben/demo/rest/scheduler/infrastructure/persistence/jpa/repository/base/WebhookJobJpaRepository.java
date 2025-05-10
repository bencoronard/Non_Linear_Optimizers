package dev.hireben.demo.rest.scheduler.infrastructure.persistence.jpa.repository.base;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface WebhookJobJpaRepository<T, ID> extends JpaRepository<T, ID> {
}
