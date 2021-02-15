package org.lisanderl;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class SensorResourceTest {

  @Test
  public void testHelloEndpoint() throws JsonProcessingException {
    var om = new ObjectMapper();
    var value = om.writeValueAsString(new SensorDataModel(123.2223, "1999-10-10"));
    System.out.println(value);
    //given()
    //    .body(value)
    //    .accept(ContentType.JSON)
    //    .contentType(ContentType.JSON)
    //    .when()
    //    .post("/sensor")
    //    .then()
    //    .statusCode(200)
    //    .body(is(value));
  }
}