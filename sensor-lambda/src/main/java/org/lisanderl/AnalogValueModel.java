package org.lisanderl;

import io.micronaut.core.annotation.Introspected;
import lombok.Value;

@Introspected
@Value
public class AnalogValueModel {
  String dateTime;
  float value;
}
