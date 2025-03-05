package com.hengleasing.portal.scheduler.http.configurations;

import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestClient;

@Configuration
public class HttpClientConfigs {

  // ---------------------------------------------------------------------------//
  // Fields
  // ---------------------------------------------------------------------------//

  @Value("${info.http.client.timeout-in-sec}")
  private int connTimeout;

  @Value("${info.http.client.pool-size}")
  private int connPoolSize;

  @Value("${info.http.client.max-conn-per-route}")
  private int maxConnPerRoute;

  // ---------------------------------------------------------------------------//
  // Methods
  // ---------------------------------------------------------------------------//

  @Bean
  ClientHttpRequestFactory clientHttpRequestFactory() {

    PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
    connectionManager.setMaxTotal(connPoolSize);
    connectionManager.setDefaultMaxPerRoute(maxConnPerRoute);

    CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(connectionManager).build();

    HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(httpClient);
    factory.setConnectTimeout(connTimeout * 1000);

    return factory;
  }

  // ---------------------------------------------------------------------------//

  @Bean
  RestClient restClient(RestClient.Builder builder, ClientHttpRequestFactory clientHttpRequestFactory,
      ResponseErrorHandler responseErrorHandler) {
    return builder
        .requestFactory(clientHttpRequestFactory)
        .defaultStatusHandler(responseErrorHandler)
        .build();
  }

}
