package tests;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static specs.Specs.baseRequestSpec;
import static specs.Specs.responseSpecCode200;
import static specs.Specs.responseSpecCode201;
import static specs.Specs.responseSpecCode204;

import io.qameta.allure.AllureId;
import io.qameta.allure.Owner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import models.CreateUserResponseModel;
import models.GetUserResponseModel;
import models.ListUserModel;
import models.UpdateUserResponseModel;
import models.UserBodyModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Owner("Dmitriy.Golovanov")
public class UsersApiTest {

  @Tag("positive")
  @AllureId("#17156")
  @DisplayName("Checking the name and position when creating a user")
  @Test
  void createUser() {

    String name = "dmitriy";
    String job = "automation quality assurance";
    UserBodyModel data = new UserBodyModel();
    data.setName(name);
    data.setJob(job);

    CreateUserResponseModel response = step("Data entry", () ->
        given(baseRequestSpec)
            .body(data)
            .when()
            .post("/users")
            .then()
            .spec(responseSpecCode201)
            .extract().as(CreateUserResponseModel.class));

    step("Checking the input data", () -> {
      assertThat(response.getName()).isEqualTo("dmitriy");
      assertThat(response.getJob()).isEqualTo("automation quality assurance");
    });
  }

  @Tag("positive")
  @AllureId("#17154")
  @DisplayName("Checking the user's mail")
  @Test
  void getUser() {
    GetUserResponseModel response = step("User View", () ->
        given(baseRequestSpec)
            .when()
            .get("/users/2")
            .then()
            .spec(responseSpecCode200)
            .extract().as(GetUserResponseModel.class));

    step("Checking that the mail belongs to this user", () -> {
      assertThat(response.getData().getEmail()).isEqualTo("janet.weaver@reqres.in");
    });
  }

  @Tag("positive")
  @AllureId("#17152")
  @DisplayName("Editing the user's place of work")
  @Test
  void editUser() {
    String name = "dmitriy";
    String job = "developer";
    String dateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDateTime.now());
    UserBodyModel data = new UserBodyModel();
    data.setName(name);
    data.setJob(job);

    UpdateUserResponseModel response = step("Data entry", () ->
        given(baseRequestSpec)
            .when()
            .patch("/users/2")
            .then()
            .spec(responseSpecCode200)
            .extract().as(UpdateUserResponseModel.class));

    step("Checking that the place of work has been edited", () -> {
      assertThat(response.getUpdatedAt()).contains(dateTime);
    });
  }

  @Tag("positive")
  @AllureId("#17151")
  @DisplayName("Deleting a user")
  @Test
  void deleteUser() {
    step("Successful user deletion", () -> {
      given(baseRequestSpec)
          .when()
          .delete("/users/2")
          .then()
          .spec(responseSpecCode204);
    });
  }

  @Tag("positive")
  @AllureId("#17157")
  @DisplayName("Checking the number of all users")
  @Test
  void getUsers() {
    ListUserModel response = step("Viewing all users", () ->
        given(baseRequestSpec)
            .when()
            .get("/users?page=2")
            .then()
            .spec(responseSpecCode200)
            .extract().as(ListUserModel.class));

    step("Checking the number of all users", () -> {
      assertThat(response.getTotal()).isEqualTo(12);
    });
  }
}
