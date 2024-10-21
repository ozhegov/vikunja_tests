package io.vikunja.api;

import io.qameta.allure.Step;
import io.vikunja.api.models.login.LoginBodyModel;
import io.vikunja.api.models.login.LoginResponseModel;

import static io.restassured.RestAssured.given;
import static io.vikunja.api.specs.BaseSpec.*;

public class LoginApi {
    @Step("Авторизуемся в приложении")
    public LoginResponseModel login(LoginBodyModel credentials) {
        return given(requestSpec)
                .body(credentials)
                .when()
                .post("/login")
                .then()
                .spec(responseSpecWithCode200)
                .extract().as(LoginResponseModel.class);
    }
}
