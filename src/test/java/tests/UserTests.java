package tests;

import base.BaseTest;
import io.qameta.allure.*;
import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utils.TestDataLoader;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@Epic("LUMA API Automation")
@Feature("User Resource")
public class UserTests extends BaseTest {

    @Test
    @DisplayName("Create a New User")
    @Severity(SeverityLevel.CRITICAL)
    public void createUserTest() {
        JSONObject requestBody = new JSONObject(TestDataLoader.load("users.json")).getJSONObject("create");

        given()
                .spec(requestSpec)
                .body(requestBody.toString())
                .when()
                .post("/users")
                .then()
                .statusCode(201)
                .body("name", equalTo(requestBody.getString("name")))
                .body("id", notNullValue());
    }

    @Test
    @DisplayName("Get a User by ID")
    @Severity(SeverityLevel.NORMAL)
    public void getUserTest() {
        given()
                .spec(requestSpec)
                .when()
                .get("/users/1")
                .then()
                .statusCode(200)
                .body("id", equalTo(1))
                .body("username", notNullValue());
    }

    @Test
    @DisplayName("Update a User")
    @Severity(SeverityLevel.NORMAL)
    public void updateUserTest() {
        JSONObject requestBody = new JSONObject(TestDataLoader.load("users.json")).getJSONObject("update");

        given()
                .spec(requestSpec)
                .body(requestBody.toString())
                .when()
                .put("/users/1")
                .then()
                .statusCode(200)
                .body("name", equalTo(requestBody.getString("name")))
                .body("email", equalTo(requestBody.getString("email")));
    }

    @Test
    @DisplayName("Delete a User")
    @Severity(SeverityLevel.MINOR)
    public void deleteUserTest() {
        given()
                .spec(requestSpec)
                .when()
                .delete("/users/1")
                .then()
                .statusCode(anyOf(is(200), is(204)));
    }
}
