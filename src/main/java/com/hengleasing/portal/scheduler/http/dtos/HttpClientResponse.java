package com.hengleasing.portal.scheduler.http.dtos;

import org.springframework.http.HttpStatusCode;

public record HttpClientResponse<T>(HttpStatusCode code, T body) {
}
