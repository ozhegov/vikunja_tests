package io.vikunja.api;

import io.qameta.allure.Step;
import io.vikunja.api.models.login.LoginResponseModel;
import io.vikunja.api.models.project.CreateProjectBodyModel;
import io.vikunja.api.models.project.CreateProjectResponseModel;

import static io.restassured.RestAssured.given;
import static io.vikunja.api.specs.BaseSpec.*;
import static io.vikunja.data.api.Headers.AUTH_HEADER;
import static io.vikunja.data.api.Headers.BEARER_PREFIX;

public class ProjectApi {
    @Step("Создаем новый проект")
    public CreateProjectResponseModel createNewProject(
            LoginResponseModel loginResponse,
            CreateProjectBodyModel projectData) {
        return given(requestSpec)
                .header(AUTH_HEADER.value, BEARER_PREFIX.value + loginResponse.getToken())
                .body(projectData)
                .when()
                .put("/projects")
                .then()
                .spec(responseSpecWithCode201)
                .extract().as(CreateProjectResponseModel.class);
    }
}
