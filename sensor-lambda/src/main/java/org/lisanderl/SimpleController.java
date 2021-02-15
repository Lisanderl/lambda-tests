package org.lisanderl;

import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import java.util.Objects;
import javax.validation.Valid;

@Controller
public class SimpleController {
  private AnalogValueModel lastModel;

  @Post("/sensor")
  public AnalogValueModel save(@Valid @Body AnalogValueModel analogValueModel) {
    lastModel = analogValueModel;
    return analogValueModel;
  }

  @Get("/sensor")
  public AnalogValueModel getModel() {

    return Objects.isNull(lastModel) ? new AnalogValueModel("", 0.0F) : lastModel;
  }
}
