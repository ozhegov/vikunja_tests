package io.vikunja.api;

import io.qameta.allure.Step;
import io.vikunja.api.models.login.LoginResponseModel;
import io.vikunja.api.models.team.CreateTeamBodyModel;
import io.vikunja.api.models.team.CreateTeamResponseModel;

import static io.restassured.RestAssured.given;
import static io.vikunja.api.specs.BaseSpec.*;
import static io.vikunja.data.api.Headers.AUTH_HEADER;
import static io.vikunja.data.api.Headers.BEARER_PREFIX;

public class TeamApi {
    @Step("Создаем новую команду")
    public CreateTeamResponseModel createNewTeam(
            LoginResponseModel loginResponse,
            CreateTeamBodyModel teamName) {
        return given(requestSpec)
                .header(AUTH_HEADER.value, BEARER_PREFIX.value + loginResponse.getToken())
                .body(teamName)
                .when()
                .put("/teams")
                .then()
                .spec(responseSpecWithCode201)
                .extract().as(CreateTeamResponseModel.class);
    }
}
