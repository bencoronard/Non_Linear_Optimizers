package dev.hireben.demo.rest.scheduler.infrastructure.http.service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CodingErrorAction;
import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import dev.hireben.demo.rest.scheduler.domain.model.JobExecResult;
import dev.hireben.demo.rest.scheduler.domain.model.Webhook;
import dev.hireben.demo.rest.scheduler.domain.service.WebhookDispatchService;
import dev.hireben.demo.rest.scheduler.domain.utility.JobExecStatus;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class WebhookDispatchServiceImpl implements WebhookDispatchService {

  // ---------------------------------------------------------------------------//
  // Fields
  // ---------------------------------------------------------------------------//

  private final RestClient client;
  private final ObjectMapper mapper;

  @Value("${app.internal.result.max-bytes}")
  private Integer MAX_BYTES;

  // ---------------------------------------------------------------------------//
  // Methods
  // ---------------------------------------------------------------------------//

  @Override
  public String dispatch(Webhook webhook) {
    JobExecResult execResult;
    try {
      execResult = client.post()
          .uri(webhook.getCallbackUrl())
          .headers(httpHeaders -> {
            if (webhook.getHeaders() != null) {
              webhook.getHeaders().forEach(httpHeaders::add);
            }
          })
          .body(webhook.getPayload())
          .exchange((_, response) -> JobExecResult.builder()
              .code(response.getStatusCode().toString())
              .message(readBodyIntoString(response.getBody()))
              .build());
    } catch (Exception e) {
      execResult = JobExecResult.builder()
          .code(JobExecStatus.UNKNOWN)
          .message(e.getMessage())
          .build();
    }

    try {
      return mapper.writeValueAsString(execResult);
    } catch (JsonProcessingException e) {
      return e.getMessage();
    }
  }

  // ---------------------------------------------------------------------------//

  private String readBodyIntoString(InputStream inputStream) throws IOException {
    if (inputStream == null) {
      return "";
    }

    byte[] raw = inputStream.readNBytes(MAX_BYTES + 3);

    CharsetDecoder decoder = StandardCharsets.UTF_8.newDecoder()
        .onMalformedInput(CodingErrorAction.IGNORE)
        .onUnmappableCharacter(CodingErrorAction.IGNORE);

    ByteBuffer byteBuffer = ByteBuffer.wrap(raw, 0, raw.length);
    CharBuffer charBuffer = CharBuffer.allocate(MAX_BYTES);

    decoder.decode(byteBuffer, charBuffer, true);
    charBuffer.flip();

    return charBuffer.toString();
  };
}
