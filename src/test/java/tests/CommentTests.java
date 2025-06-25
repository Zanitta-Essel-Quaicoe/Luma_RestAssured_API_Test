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
@Feature("Comment Resource")
public class CommentTests extends BaseTest {

    @Test
    @DisplayName("Get All Comments")
    @Severity(SeverityLevel.NORMAL)
    public void getAllCommentsTest() {
        given()
                .spec(requestSpec)
                .when()
                .get("/comments")
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }

    @Test
    @DisplayName("Get Comment by ID")
    @Severity(SeverityLevel.NORMAL)
    public void getCommentByIdTest() {
        given()
                .spec(requestSpec)
                .when()
                .get("/comments/1")
                .then()
                .statusCode(200)
                .body("id", equalTo(1))
                .body("email", containsString("@"));
    }

    @Test
    @DisplayName("Create a Comment")
    @Severity(SeverityLevel.CRITICAL)
    public void createCommentTest() {
        JSONObject requestBody = new JSONObject(TestDataLoader.load("comments.json")).getJSONObject("create");

        given()
                .spec(requestSpec)
                .body(requestBody.toString())
                .when()
                .post("/comments")
                .then()
                .statusCode(201)
                .body("name", equalTo(requestBody.getString("name")))
                .body("id", notNullValue());
    }

    @Test
    @DisplayName("Update a Comment")
    @Severity(SeverityLevel.NORMAL)
    public void updateCommentTest() {
        JSONObject requestBody = new JSONObject(TestDataLoader.load("comments.json")).getJSONObject("update");

        given()
                .spec(requestSpec)
                .body(requestBody.toString())
                .when()
                .put("/comments/1")
                .then()
                .statusCode(200)
                .body("name", equalTo(requestBody.getString("name")))
                .body("email", equalTo(requestBody.getString("email")));
    }

    @Test
    @DisplayName("Delete a Comment")
    @Severity(SeverityLevel.MINOR)
    public void deleteCommentTest() {
        given()
                .spec(requestSpec)
                .when()
                .delete("/comments/1")
                .then()
                .statusCode(anyOf(is(200), is(204)));
    }
}
