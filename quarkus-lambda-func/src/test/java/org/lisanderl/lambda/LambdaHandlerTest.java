package org.lisanderl.lambda;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import io.quarkus.amazon.lambda.test.LambdaClient;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class LambdaHandlerTest {

  @Test
  public void testSimpleLambdaSuccess() throws Exception {
    SensorValue in = new SensorValue("2020-10-01", 22.5);

    SensorValueResponse out = LambdaClient.invoke(SensorValueResponse.class, in);
    assertEquals(in.getDateTime(), out.getDateTime());
    assertNotNull(out.getAwsRequestId());
  }
}
