package tests;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static specs.Specs.baseRequestSpec;
import static specs.Specs.responseSpecCode400;

import io.qameta.allure.AllureId;
import io.qameta.allure.Owner;
import models.LoginBodyModel;
import models.LoginErrorResponseModel;
import models.RegisterErrorResponseModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Owner("Dmitriy.Golovanov")
public class AuthApiTest {

  @Tag("negative")
  @AllureId("#17155")
  @DisplayName("Login-Unsuccessful")
  @Test
  void loginUnsuccessful() {

    String email = "peter@klaven";
    LoginBodyModel data = new LoginBodyModel();
    data.setEmail(email);

    LoginErrorResponseModel response = step("Data entry", () ->
        given(baseRequestSpec)
            .body(data)
            .when()
            .post("/login")
            .then()
            .spec(responseSpecCode400)
            .extract().as(LoginErrorResponseModel.class));

    step("Error checking", () -> {
      assertThat(response.getError()).isEqualTo("Missing password");
    });
  }

  @Tag("negative")
  @AllureId("#17153")
  @DisplayName("Register-Unsuccessful")
  @Test
  void registerUnsuccessful() {

    String email = "sydney@fife";
    LoginBodyModel data = new LoginBodyModel();
    data.setEmail(email);

    RegisterErrorResponseModel response = step("Data entry", () ->
        given(baseRequestSpec)
            .body(data)
            .when()
            .post("/register")
            .then()
            .spec(responseSpecCode400)
            .extract().as(RegisterErrorResponseModel.class));

    step("Error checking", () -> {
      assertThat(response.getError()).isEqualTo("Missing password");
    });
  }
}
