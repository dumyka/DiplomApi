package specs;

import static helpers.CustomApiListener.withCustomTemplates;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.BODY;
import static io.restassured.filter.log.LogDetail.STATUS;
import static io.restassured.http.ContentType.JSON;

import config.ApiConfig;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.aeonbits.owner.ConfigFactory;

public class Specs {
  private static ApiConfig config = ConfigFactory.create(ApiConfig.class);

  public static RequestSpecification baseRequestSpec = with()
      .baseUri(config.baseApiUri())
      .basePath("/api")
      .log().uri()
      .log().headers()
      .log().body()
      .filter(withCustomTemplates())
      .contentType(JSON);


  public static ResponseSpecification responseSpecCode200 = new ResponseSpecBuilder()
      .log(STATUS)
      .log(BODY)
      .expectStatusCode(200)
      .build();

  public static ResponseSpecification responseSpecCode201 = new ResponseSpecBuilder()
      .log(STATUS)
      .log(BODY)
      .expectStatusCode(201)
      .build();

  public static ResponseSpecification responseSpecCode204 = new ResponseSpecBuilder()
      .log(STATUS)
      .log(BODY)
      .expectStatusCode(204)
      .build();

  public static ResponseSpecification responseSpecCode400 = new ResponseSpecBuilder()
      .log(STATUS)
      .log(BODY)
      .expectStatusCode(400)
      .build();
}
