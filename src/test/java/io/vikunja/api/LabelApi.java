package io.vikunja.api;

import io.qameta.allure.Step;
import io.restassured.common.mapper.TypeRef;
import io.vikunja.api.models.labels.addToTask.validData.AddLabelToTaskBodyModel;
import io.vikunja.api.models.labels.addToTask.validData.AddLabelToTaskResponseModel;
import io.vikunja.api.models.labels.addToTask.invalidData.AddLabelToTaskWithInvalidDataResponseModel;
import io.vikunja.api.models.labels.createLabel.CreateLabelBodyModel;
import io.vikunja.api.models.labels.createLabel.CreateLabelResponseModel;
import io.vikunja.api.models.login.LoginResponseModel;
import io.vikunja.api.models.task.CreateTaskResponseModel;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static io.vikunja.api.specs.BaseSpec.*;
import static io.vikunja.api.specs.LabelSpec.*;
import static io.vikunja.data.api.Headers.AUTH_HEADER;
import static io.vikunja.data.api.Headers.BEARER_PREFIX;
import static org.assertj.core.api.Assertions.assertThat;

public class LabelApi {
    @Step("Создаем новый лейбл с валидными данными")
    public CreateLabelResponseModel createNewLabel(
            LoginResponseModel loginResponse,
            CreateLabelBodyModel labelData) {
        return given(requestSpec)
                .header(AUTH_HEADER.value, BEARER_PREFIX.value + loginResponse.getToken())
                .body(labelData)
                .when()
                .put("/labels")
                .then()
                .spec(labelCreateResponseSpecWithCode201)
                .extract().as(CreateLabelResponseModel.class);
    }

    @Step("Создаем {times} лейбла(ов) с валидными данными")
    public List<CreateLabelResponseModel> createNumberOfLabels(
            LoginResponseModel loginResponse,
            CreateLabelBodyModel labelData,
            int times) {
        List<CreateLabelResponseModel> createResponses = new ArrayList<>();
        for (int i = 0; i < times; i++) {
            CreateLabelResponseModel createResponse = createNewLabel(loginResponse, labelData);
            createResponses.add(createResponse);
        }
        return createResponses;
    }

    @Step("Добавляем принадлежащий пользователю лейбл к задаче")
    public AddLabelToTaskResponseModel addLabelToTask(
            LoginResponseModel loginResponse,
            AddLabelToTaskBodyModel addLabelData,
            CreateTaskResponseModel taskResponse) {
        return given(requestSpec)
                .header(AUTH_HEADER.value, BEARER_PREFIX.value + loginResponse.getToken())
                .body(addLabelData)
                .pathParam("id", taskResponse.getId())
                .when()
                .put("/tasks/{id}/labels")
                .then()
                .spec(labelAddToTaskResponseSpecWithCode201)
                .extract().as(AddLabelToTaskResponseModel.class);
    }

    @Step("Добавляем лейбл с несуществующим ID к задаче")
    public AddLabelToTaskWithInvalidDataResponseModel addLabelWithNonExtIDToTask(
            LoginResponseModel loginResponse,
            AddLabelToTaskBodyModel addLabelToTaskData,
            CreateTaskResponseModel taskResponse) {
        return given(requestSpec)
                .header(AUTH_HEADER.value, BEARER_PREFIX.value + loginResponse.getToken())
                .body(addLabelToTaskData)
                .pathParam("id", taskResponse.getId())
                .when()
                .put("/tasks/{id}/labels")
                .then()
                .spec(labelAddToTaskResponseSpecWithCode404)
                .extract().as(AddLabelToTaskWithInvalidDataResponseModel.class);
    }

    @Step("Добавляем принадлежащие пользователю лейблы к задаче")
    public List<AddLabelToTaskResponseModel> addNumberOfLabelsToTask(
            LoginResponseModel loginResponse,
            List<CreateLabelResponseModel> createResponses,
            CreateTaskResponseModel taskResponse) {
        List<AddLabelToTaskResponseModel> addLabelResponses = new ArrayList<>();
        List<AddLabelToTaskBodyModel> addLabelDataList = new ArrayList<>();
        for (CreateLabelResponseModel createResponse : createResponses) {
            AddLabelToTaskBodyModel addLabelData = new AddLabelToTaskBodyModel(createResponse.getId());
            addLabelDataList.add(addLabelData);
        }
        for (AddLabelToTaskBodyModel addLabelData : addLabelDataList) {
            AddLabelToTaskResponseModel response = addLabelToTask(loginResponse, addLabelData, taskResponse);
            addLabelResponses.add(response);
        }
        return addLabelResponses;
    }

    @Step("Получаем список ID лейблов добавленных к задаче")
    public List<Integer> getLabelsIds(List<AddLabelToTaskResponseModel> responses) {
        List<Integer> extractedIds = new ArrayList<>();
        for (AddLabelToTaskResponseModel response : responses) {
            int labelId = response.getLabelId();
            extractedIds.add(labelId);
        }
        return extractedIds;
    }

    @Step("Получаем данные по всем лейблам добавленным к задаче")
    public List<CreateLabelResponseModel> getAllLabelsData(
            LoginResponseModel loginResponse,
            CreateTaskResponseModel taskResponse) {
        return given(requestSpec)
                .header(AUTH_HEADER.value, BEARER_PREFIX.value + loginResponse.getToken())
                .pathParam("id", taskResponse.getId())
                .when()
                .get("/tasks/{id}/labels")
                .then()
                .spec(labelGetAllResponseSpecWithCode200)
                .extract().as(new TypeRef<>() {
                });
    }

    @Step("Проверяем, что ID лейбла в ответе соответствует ID в запросе")
    public void checkRespAndReqLabelEql(
            AddLabelToTaskResponseModel addLabelToTaskResponse,
            CreateLabelResponseModel createLabelResponse) {
        assertThat(addLabelToTaskResponse.getLabelId()).isEqualTo(createLabelResponse.getId());
    }

    @Step("Проверяем, что дата создания лейбла в ответе соответствует текущей дате ")
    public void checkCreatedDateEqlCurrentDate(
            AddLabelToTaskResponseModel addLabelToTaskResponse,
            String currentDay) {
        assertThat(addLabelToTaskResponse.getCreated()).contains(currentDay);
    }

    @Step("Проверяем, что в ответе отображается сообщение - {createLabelWithNonExtIdMsg}")
    public void checkCreateLabelWithNonExtIdMsg(
            AddLabelToTaskWithInvalidDataResponseModel createLabelWithNonExtIdResponse,
            String createLabelWithNonExtIdMsg) {
        assertThat(createLabelWithNonExtIdResponse.getMessage()).isEqualTo(createLabelWithNonExtIdMsg);
    }

    @Step("Проверяем, что ID лейблов в ответе соответствуют ID лейблам ранее добавленным к задаче")
    public void checkLabelsIdsInResponse(
            List<CreateLabelResponseModel> labelResponseData,
            List<Integer> expectedIds) {
        List<Integer> actualIds = labelResponseData.stream()
                .map(CreateLabelResponseModel::getId)
                .toList();
        assertThat(actualIds).containsExactlyInAnyOrderElementsOf(expectedIds);
    }
}
