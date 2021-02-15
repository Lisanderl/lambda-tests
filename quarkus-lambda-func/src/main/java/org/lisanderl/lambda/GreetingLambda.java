package org.lisanderl.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class GreetingLambda implements RequestHandler<SensorValue, SensorValueResponse> {

  @Override
  public SensorValueResponse handleRequest(SensorValue input, Context context) {
    return new SensorValueResponse(
        context.getAwsRequestId(), input.getDateTime(), input.getAnalogValue());
  }
}
