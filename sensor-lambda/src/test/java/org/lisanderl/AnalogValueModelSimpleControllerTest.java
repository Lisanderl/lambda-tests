package org.lisanderl;

import com.amazonaws.serverless.exceptions.ContainerInitializationException;
import com.amazonaws.serverless.proxy.internal.testutils.AwsProxyRequestBuilder;
import com.amazonaws.serverless.proxy.internal.testutils.MockLambdaContext;
import com.amazonaws.serverless.proxy.model.AwsProxyRequest;
import com.amazonaws.serverless.proxy.model.AwsProxyResponse;
import com.amazonaws.services.lambda.runtime.Context;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.micronaut.function.aws.proxy.MicronautLambdaHandler;
import io.micronaut.http.HttpHeaders;
import io.micronaut.http.HttpMethod;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MediaType;
import java.time.LocalDateTime;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class AnalogValueModelSimpleControllerTest {

  private static MicronautLambdaHandler handler;
  private static Context lambdaContext = new MockLambdaContext();
  private static ObjectMapper objectMapper;

  @BeforeAll
  public static void setupSpec() {
    try {
      handler = new MicronautLambdaHandler();
      objectMapper = handler.getApplicationContext().getBean(ObjectMapper.class);
    } catch (ContainerInitializationException e) {
      e.printStackTrace();
    }
  }

  @AfterAll
  public static void cleanupSpec() {
    handler.getApplicationContext().close();
  }

  @Test
  void testSaveBook() throws JsonProcessingException {
    AnalogValueModel analogValueModel = new AnalogValueModel("some str", 3222.3F);
    String json = objectMapper.writeValueAsString(analogValueModel);
    AwsProxyRequest request = new AwsProxyRequestBuilder("/sensor", HttpMethod.POST.toString())
        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
        .body(json)
        .build();
    AwsProxyResponse response = handler.handleRequest(request, lambdaContext);
    Assertions.assertEquals(HttpStatus.OK.getCode(), response.getStatusCode());
    var saved = objectMapper.readValue(response.getBody(), AnalogValueModel.class);
    Assertions.assertEquals(saved.getValue(), analogValueModel.getValue());
    Assertions.assertNotNull(saved.getDateTime());
  }

  @Test
  void testGetBook() throws JsonProcessingException {

    AwsProxyRequest request = new AwsProxyRequestBuilder("/sensor", HttpMethod.GET.toString())
        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
        .build();
    AwsProxyResponse response = handler.handleRequest(request, lambdaContext);
    Assertions.assertEquals(HttpStatus.OK.getCode(), response.getStatusCode());
    var saved = objectMapper.readValue(response.getBody(), AnalogValueModel.class);
    Assertions.assertEquals(saved, new AnalogValueModel(null, 0.0F) );

  }
}
