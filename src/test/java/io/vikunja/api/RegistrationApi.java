package io.vikunja.api;

import io.qameta.allure.Step;
import io.vikunja.api.models.registration.RegistrationBodyModel;
import io.vikunja.api.models.registration.RegistrationResponseModel;

import static io.restassured.RestAssured.given;
import static io.vikunja.api.specs.BaseSpec.*;

public class RegistrationApi {
    @Step("Регистрируем нового пользователя")
    public RegistrationResponseModel registration(RegistrationBodyModel regData) {
        return given(requestSpec)
                .body(regData)
                .when()
                .post("/register")
                .then()
                .spec(responseSpecWithCode200)
                .extract().as(RegistrationResponseModel.class);
    }
}
