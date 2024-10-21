package io.vikunja.tests.web;

import io.qameta.allure.*;
import io.vikunja.api.models.labels.createLabel.CreateLabelResponseModel;
import io.vikunja.api.models.project.CreateProjectResponseModel;
import io.vikunja.api.models.task.CreateTaskResponseModel;
import io.vikunja.api.models.team.CreateTeamResponseModel;
import io.vikunja.helpers.annotations.WithAuthLogin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

@Owner("Maksim Ozhegov")
@Epic("Поиск по приложению")
@Feature("Поиск по основным сущностям")
@Tags({@Tag("Web"), @Tag("Search")})
@DisplayName("Проверки функционирования поиска по основным сущностям приложения")

public class SearchTests extends TestBase {

    @Test
    @WithAuthLogin
    @Story("Поиск по названию проекта")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("При вводе в поле поиска символа '+' перед названием проекта в результатах поиска отображаются данные по искомому проекту")
    public void projectDataVisibleAfterSearchingPlusAndProjectName() {
        CreateProjectResponseModel createProjectResponse = projectApi
                .createNewProject(loginResponse, data.projectData);
        CreateTaskResponseModel createTaskResponse = taskApi
                .createNewTask(loginResponse, data.taskData, createProjectResponse);
        homePage
                .openHomePage();
        topBar
                .clickSearchIcon();
        searchModal
                .setPlusAndProjectName(createProjectResponse.getTitle())
                .checkSearchValueAppears(createProjectResponse.getTitle())
                .checkTaskNameAppears(createTaskResponse.getTitle());
    }

    @Test
    @WithAuthLogin
    @Story("Поиск по названию команды")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("При вводе в поле поиска символа '@' перед названием команды в результатах поиска отображается название искомой команды")
    public void teamNameVisibleAfterSearchingAtAndTeamName() {
        CreateTeamResponseModel createTeamResponse = teamApi
                .createNewTeam(loginResponse, data.teamData);
        homePage
                .openHomePage();
        topBar
                .clickSearchIcon();
        searchModal
                .setAtAndTeamName(createTeamResponse.getName())
                .checkSearchValueAppears(createTeamResponse.getName());
    }

    @Test
    @WithAuthLogin
    @Story("Поиск по названию лейбла")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("При вводе в поле поиска символа '*' перед названием лейбла в круглых скобках, в результатах поиска отображается название искомого лейбла")
    public void labelNameVisibleAfterSearchingAsteriskAndLabelName() {
        CreateLabelResponseModel createLabelResponse = labelApi
                .createNewLabel(loginResponse, data.labelData);
        homePage
                .openHomePage();
        topBar
                .clickSearchIcon();
        searchModal
                .setAsteriskAndLabelName(createLabelResponse.getTitle())
                .checkLabelNameAppears(createLabelResponse.getTitle());
    }
}
