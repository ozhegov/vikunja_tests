package io.vikunja.api;

import io.qameta.allure.Step;
import io.vikunja.api.models.login.LoginResponseModel;
import io.vikunja.api.models.project.CreateProjectResponseModel;
import io.vikunja.api.models.task.CreateTaskBodyModel;
import io.vikunja.api.models.task.CreateTaskResponseModel;

import static io.restassured.RestAssured.given;
import static io.vikunja.api.specs.BaseSpec.*;
import static io.vikunja.data.api.Headers.AUTH_HEADER;
import static io.vikunja.data.api.Headers.BEARER_PREFIX;

public class TaskApi {
    @Step("Создаем новую задачу в проекте")
    public CreateTaskResponseModel createNewTask(
            LoginResponseModel loginResponse,
            CreateTaskBodyModel taskData,
            CreateProjectResponseModel createProjectResponse) {
        return given(requestSpec)
                .header(AUTH_HEADER.value, BEARER_PREFIX.value + loginResponse.getToken())
                .body(taskData)
                .pathParam("id", createProjectResponse.getId())
                .when()
                .put("/projects/{id}/tasks")
                .then()
                .spec(responseSpecWithCode201)
                .extract().as(CreateTaskResponseModel.class);
    }
}
