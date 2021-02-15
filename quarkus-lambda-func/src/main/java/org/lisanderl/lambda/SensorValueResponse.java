package org.lisanderl.lambda;

import lombok.Value;

@Value
public class SensorValueResponse {
  String awsRequestId;
  String dateTime;
  double analogValue;
}
