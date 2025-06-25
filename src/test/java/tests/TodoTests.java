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
@Feature("Todo Resource")
public class TodoTests extends BaseTest {

    @Test
    @DisplayName("Get All Todos")
    @Severity(SeverityLevel.NORMAL)
    public void getAllTodosTest() {
        given()
                .spec(requestSpec)
                .when()
                .get("/todos")
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }

    @Test
    @DisplayName("Get Todo by ID")
    @Severity(SeverityLevel.NORMAL)
    public void getTodoByIdTest() {
        given()
                .spec(requestSpec)
                .when()
                .get("/todos/1")
                .then()
                .statusCode(200)
                .body("id", equalTo(1))
                .body("title", not(emptyOrNullString()));
    }

    @Test
    @DisplayName("Create a Todo")
    @Severity(SeverityLevel.CRITICAL)
    public void createTodoTest() {
        JSONObject requestBody = new JSONObject(TestDataLoader.load("todos.json")).getJSONObject("create");

        given()
                .spec(requestSpec)
                .body(requestBody.toString())
                .when()
                .post("/todos")
                .then()
                .statusCode(201)
                .body("title", equalTo(requestBody.getString("title")))
                .body("completed", equalTo(requestBody.getBoolean("completed")));
    }

    @Test
    @DisplayName("Update a Todo")
    @Severity(SeverityLevel.NORMAL)
    public void updateTodoTest() {
        JSONObject requestBody = new JSONObject(TestDataLoader.load("todos.json")).getJSONObject("update");

        given()
                .spec(requestSpec)
                .body(requestBody.toString())
                .when()
                .put("/todos/1")
                .then()
                .statusCode(200)
                .body("title", equalTo(requestBody.getString("title")))
                .body("completed", equalTo(requestBody.getBoolean("completed")));
    }

    @Test
    @DisplayName("Delete a Todo")
    @Severity(SeverityLevel.MINOR)
    public void deleteTodoTest() {
        given()
                .spec(requestSpec)
                .when()
                .delete("/todos/1")
                .then()
                .statusCode(anyOf(is(200), is(204)));
    }
}
