package io.vikunja.tests.api;

import io.qameta.allure.*;
import io.vikunja.api.models.labels.addToTask.validData.AddLabelToTaskBodyModel;
import io.vikunja.api.models.labels.addToTask.validData.AddLabelToTaskResponseModel;
import io.vikunja.api.models.labels.addToTask.invalidData.AddLabelToTaskWithInvalidDataResponseModel;
import io.vikunja.api.models.labels.createLabel.CreateLabelResponseModel;
import io.vikunja.api.models.project.CreateProjectResponseModel;
import io.vikunja.api.models.task.CreateTaskResponseModel;
import io.vikunja.data.api.ApiMessages;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import java.util.List;

@Owner("Maksim Ozhegov")
@Epic("Маркировка задач")
@Feature("Маркировка задач с помощью лейблов")
@Tags({@Tag("API"), @Tag("Label")})
@DisplayName("API проверки функционирования лейблов")
public class LabelTests extends TestBase {

    @Test
    @Story("Добавление лейбла к задаче")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("При добавлении принадлежащего пользователю лейбла к задаче его ID корректно отображается в ответе метода")
    public void checkLabelIdInReqAndRespAreEql() {
        CreateProjectResponseModel createProjectResponse = projectApi
                .createNewProject(loginResponse, data.projectData);
        CreateTaskResponseModel createTaskResponse = taskApi
                .createNewTask(loginResponse, data.taskData, createProjectResponse);
        CreateLabelResponseModel createLabelResponse = labelApi
                .createNewLabel(loginResponse, data.labelData);
        AddLabelToTaskBodyModel addLabelData = new AddLabelToTaskBodyModel(createLabelResponse.getId());
        AddLabelToTaskResponseModel addLabelToTaskResponse = labelApi
                .addLabelToTask(loginResponse, addLabelData, createTaskResponse);
        labelApi
                .checkRespAndReqLabelEql(addLabelToTaskResponse, createLabelResponse);
    }

    @Test
    @Story("Добавление лейбла к задаче")
    @Severity(SeverityLevel.MINOR)
    @DisplayName("При добавлении принадлежащего пользователю лейбла к задаче дата его создания в ответе метода соответствует текущей")
    public void checkLabelCreatedDateAndCurrentDateAreEql() {
        CreateProjectResponseModel createProjectResponse = projectApi
                .createNewProject(loginResponse, data.projectData);
        CreateTaskResponseModel createTaskResponse = taskApi
                .createNewTask(loginResponse, data.taskData, createProjectResponse);
        CreateLabelResponseModel createLabelResponse = labelApi
                .createNewLabel(loginResponse, data.labelData);
        AddLabelToTaskBodyModel addLabelData = new AddLabelToTaskBodyModel(createLabelResponse.getId());
        AddLabelToTaskResponseModel addLabelToTaskResponse = labelApi
                .addLabelToTask(loginResponse, addLabelData, createTaskResponse);
        labelApi
                .checkCreatedDateEqlCurrentDate(addLabelToTaskResponse, data.currentDay);
    }

    @Test
    @Story("Добавление лейбла к задаче")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("При добавлении лейбла с несуществующим ID к задаче в ответе метода отображается сообщение об ошибке")
    public void errorMsgIsVisibleAfterRequestWithNonExtLabelId() {
        CreateProjectResponseModel createProjectResponse = projectApi
                .createNewProject(loginResponse, data.projectData);
        CreateTaskResponseModel createTaskResponse = taskApi
                .createNewTask(loginResponse, data.taskData, createProjectResponse);
        AddLabelToTaskWithInvalidDataResponseModel addLabelToTaskResponse = labelApi
                .addLabelWithNonExtIDToTask(loginResponse, data.labelNonExistingId, createTaskResponse);
        labelApi
                .checkCreateLabelWithNonExtIdMsg(addLabelToTaskResponse, ApiMessages.LABEL_NOT_FOUND_ID_MSG.message);
    }

    @Test
    @Story("Получение данных по лейблам")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("При запросе данных по добавленным к задаче лейблам в ответе метода отображаются ID ранее добавленных лейблов")
    public void labelIdsInRespAreEqlToIdsAddedBeforeLabels() {
        CreateProjectResponseModel createProjectResponse = projectApi
                .createNewProject(loginResponse, data.projectData);
        CreateTaskResponseModel createTaskResponse = taskApi
                .createNewTask(loginResponse, data.taskData, createProjectResponse);
        List<CreateLabelResponseModel> createLabelResponses = labelApi
                .createNumberOfLabels(loginResponse, data.labelData, data.numberOfLabels);
        List<AddLabelToTaskResponseModel> addLabelToTaskResponses = labelApi
                .addNumberOfLabelsToTask(loginResponse, createLabelResponses, createTaskResponse);
        List<Integer> expectedIds = labelApi
                .getLabelsIds(addLabelToTaskResponses);
        List<CreateLabelResponseModel> allLabelsData = labelApi
                .getAllLabelsData(loginResponse, createTaskResponse);
        labelApi
                .checkLabelsIdsInResponse(allLabelsData, expectedIds);
    }
}
