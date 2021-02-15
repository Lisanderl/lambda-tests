package org.lisanderl;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/sensor")
public class SensorResource {

  @POST
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public SensorDataModel hello(SensorDataModel dataModel) {

    return new SensorDataModel(dataModel.getValue(), dataModel.getDateTime());
  }
}