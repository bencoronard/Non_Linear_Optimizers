package com.hengleasing.portal.scheduler.filters.configurations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hengleasing.portal.scheduler.filters.HttpHeaderFilter;
import com.hengleasing.portal.scheduler.registry.services.ServiceRegistryService;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class FilterConfigs {

  // ---------------------------------------------------------------------------//
  // Dependencies
  // ---------------------------------------------------------------------------//

  private final ServiceRegistryService serviceRegistry;

  // ---------------------------------------------------------------------------//
  // Fields
  // ---------------------------------------------------------------------------//

  @Value("${info.api.internal.secret-key}")
  private String API_KEY;

  // ---------------------------------------------------------------------------//
  // Methods
  // ---------------------------------------------------------------------------//

  @Bean
  FilterRegistrationBean<HttpHeaderFilter> httpHeaderFilter() {
    FilterRegistrationBean<HttpHeaderFilter> filter = new FilterRegistrationBean<>();
    filter.setFilter(new HttpHeaderFilter(serviceRegistry, API_KEY));
    filter.setOrder(0);
    filter.addUrlPatterns("/scheduler/api/v1/*");
    return filter;
  }

}
