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
@Feature("Post Resource")
public class PostTests extends BaseTest {

    @Test
    @DisplayName("Create a New Post")
    @Severity(SeverityLevel.CRITICAL)
    public void createPostTest() {
        JSONObject requestBody = new JSONObject(TestDataLoader.load("posts.json")).getJSONObject("create");

        given()
                .spec(requestSpec)
                .body(requestBody.toString())
                .when()
                .post("/posts")
                .then()
                .statusCode(201)
                .body("title", equalTo(requestBody.getString("title")))
                .body("id", notNullValue());
    }

    @Test
    @DisplayName("Get a Post by ID")
    @Severity(SeverityLevel.NORMAL)
    public void getPostTest() {
        given()
                .spec(requestSpec)
                .when()
                .get("/posts/1")
                .then()
                .statusCode(200)
                .body("id", equalTo(1));
    }

    @Test
    @DisplayName("Update a Post")
    @Severity(SeverityLevel.NORMAL)
    public void updatePostTest() {
        JSONObject requestBody = new JSONObject(TestDataLoader.load("posts.json")).getJSONObject("update");

        given()
                .spec(requestSpec)
                .body(requestBody.toString())
                .when()
                .put("/posts/1")
                .then()
                .statusCode(200)
                .body("title", equalTo(requestBody.getString("title")));
    }

    @Test
    @DisplayName("Delete a Post")
    @Severity(SeverityLevel.MINOR)
    public void deletePostTest() {
        given()
                .spec(requestSpec)
                .when()
                .delete("/posts/1")
                .then()
                .statusCode(anyOf(is(200), is(204)));
    }
}
