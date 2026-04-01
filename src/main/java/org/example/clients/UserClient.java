package org.example.clients;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.example.constants.Endpoints;
import org.example.models.UserModel;

import static io.restassured.RestAssured.given;

public class UserClient {
    @Step("Создать пользователя через API")
    public Response createUser(UserModel user) {
        return given().header("Content-type", "application/json")
                .body(user).post(Endpoints.BASE_URL + "/api/auth/register")
                .then()
                .extract().response();
    }

    @Step("Удалить пользователя через API")
    public void deleteUser(String token) {
        if (token != null) {
            given().header("Authorization", token)
                    .delete(Endpoints.BASE_URL + "/api/auth/user");
        }
    }
}