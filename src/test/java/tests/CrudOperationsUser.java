package tests;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static specs.CreateUserSpecs.createUserRequestSpec;
import static specs.CreateUserSpecs.createUserResponseSpec;
import static specs.DeleteUserSpecs.deletedUserRequestSpec;
import static specs.DeleteUserSpecs.deletedUserResponseSpec;
import static specs.GetUserSpecs.userRequestSpec;
import static specs.GetUserSpecs.userResponseSpec;
import static specs.ListUserSpecs.listUserRequestSpec;
import static specs.ListUserSpecs.listUserResponseSpec;
import static specs.LoginSpec.loginRequestSpec;
import static specs.LoginSpec.loginResponseSpec;
import static specs.UpdateUserSpec.updatedUserRequestSpec;
import static specs.UpdateUserSpec.updatedUserResponseSpec;

import io.qameta.allure.AllureId;
import io.qameta.allure.Owner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import models.CreateUserResponseModel;
import models.GetUserResponseModel;
import models.ListUserModel;
import models.LoginBodyModel;
import models.LoginErrorResponseModel;
import models.RegisterErrorResponseModel;
import models.UpdateUserResponseModel;
import models.UserBodyModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class CrudOperationsUser {

  @Tag("positive")
  @AllureId("#17156")
  @DisplayName("Checking the name and position when creating a user")
  @Owner("Dmitriy.Golovanov")
  @Test
  void createUser() {

    String name = "dmitriy";
    String job = "automation quality assurance";
    UserBodyModel data = new UserBodyModel();
    data.setName(name);
    data.setJob(job);

    CreateUserResponseModel response = step("Data entry", () ->
        given(createUserRequestSpec)
            .body(data)
            .when()
            .post("/users")
            .then()
            .spec(createUserResponseSpec)
            .extract().as(CreateUserResponseModel.class));

    step("Checking the input data", () -> {
      assertThat(response.getName()).isEqualTo("dmitriy");
      assertThat(response.getJob()).isEqualTo("automation quality assurance");
    });
  }

  @Tag("positive")
  @AllureId("#17154")
  @DisplayName("Checking the user's mail")
  @Owner("Dmitriy.Golovanov")
  @Test
  void getUser() {
    GetUserResponseModel response = step("User View", () ->
        given(userRequestSpec)
            .when()
            .get("/users/2")
            .then()
            .spec(userResponseSpec)
            .extract().as(GetUserResponseModel.class));

    step("Checking that the mail belongs to this user", () -> {
      assertThat(response.getData().getEmail()).isEqualTo("janet.weaver@reqres.in");
    });
  }

  @Tag("positive")
  @AllureId("#17152")
  @DisplayName("Editing the user's place of work")
  @Owner("Dmitriy.Golovanov")
  @Test
  void editUser() {
    String name = "dmitriy";
    String job = "developer";
    String dateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDateTime.now());
    UserBodyModel data = new UserBodyModel();
    data.setName(name);
    data.setJob(job);

    UpdateUserResponseModel response = step("Data entry", () ->
        given(updatedUserRequestSpec)
            .when()
            .patch("/users/2")
            .then()
            .spec(updatedUserResponseSpec)
            .extract().as(UpdateUserResponseModel.class));

    step("Checking that the place of work has been edited", () -> {
      assertThat(response.getUpdatedAt()).contains(dateTime);
    });
  }

  @Tag("positive")
  @AllureId("#17151")
  @DisplayName("Deleting a user")
  @Owner("Dmitriy.Golovanov")
  @Test
  void deleteUser() {
    step("Successful user deletion", () -> {
      given(deletedUserRequestSpec)
          .when()
          .delete("/users/2")
          .then()
          .spec(deletedUserResponseSpec);
    });
  }

  @Tag("positive")
  @AllureId("#17157")
  @DisplayName("Checking the number of all users")
  @Owner("Dmitriy.Golovanov")
  @Test
  void getUsers() {
    ListUserModel response = step("Viewing all users", () ->
        given(listUserRequestSpec)
            .when()
            .get("/users?page=2")
            .then()
            .spec(listUserResponseSpec)
            .extract().as(ListUserModel.class));

    step("Checking the number of all users", () -> {
      assertThat(response.getTotal()).isEqualTo(12);
    });
  }

  @Tag("negative")
  @AllureId("#17155")
  @DisplayName("Login-Unsuccessful")
  @Owner("Dmitriy.Golovanov")
  @Test
  void loginUnsuccessful() {

    String email = "peter@klaven";
    LoginBodyModel data = new LoginBodyModel();
    data.setEmail(email);

    LoginErrorResponseModel response = step("Data entry", () ->
        given(loginRequestSpec)
            .body(data)
            .when()
            .post("/login")
            .then()
            .spec(loginResponseSpec)
            .extract().as(LoginErrorResponseModel.class));

    step("Error checking", () -> {
      assertThat(response.getError()).isEqualTo("Missing password");
    });
  }

  @Tag("negative")
  @AllureId("#17153")
  @DisplayName("Register-Unsuccessful")
  @Owner("Dmitriy.Golovanov")
  @Test
  void registerUnsuccessful() {

    String email = "sydney@fife";
    LoginBodyModel data = new LoginBodyModel();
    data.setEmail(email);

    RegisterErrorResponseModel response = step("Data entry", () ->
        given(loginRequestSpec)
            .body(data)
            .when()
            .post("/register")
            .then()
            .spec(loginResponseSpec)
            .extract().as(RegisterErrorResponseModel.class));

    step("Error checking", () -> {
      assertThat(response.getError()).isEqualTo("Missing password");
    });
  }
}
