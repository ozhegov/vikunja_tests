package io.vikunja.api;

import io.qameta.allure.Step;
import io.vikunja.api.models.login.LoginResponseModel;
import io.vikunja.api.models.token.createToken.invalidData.CreateTokenWithInvalidExpDateBodyModel;
import io.vikunja.api.models.token.createToken.invalidData.CreateTokenWithInvalidDataResponseModel;
import io.vikunja.api.models.token.createToken.validData.CreateTokenBodyModel;
import io.vikunja.api.models.token.createToken.validData.CreateTokenResponseModel;
import io.vikunja.api.models.token.deleteToken.DeleteTokenResponseModel;

import static io.restassured.RestAssured.given;
import static io.vikunja.api.specs.BaseSpec.*;
import static io.vikunja.api.specs.TokenSpec.*;
import static io.vikunja.data.api.Headers.AUTH_HEADER;
import static io.vikunja.data.api.Headers.BEARER_PREFIX;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TokenApi {
    @Step("Создаем новый токен с валидными данными")
    public CreateTokenResponseModel createNewToken(
            LoginResponseModel loginResponse,
            CreateTokenBodyModel validTokenData) {
        return given(requestSpec)
                .header(AUTH_HEADER.value, BEARER_PREFIX.value + loginResponse.getToken())
                .body(validTokenData)
                .when()
                .put("/tokens")
                .then()
                .spec(tokenCreateResponseSpecWithCode201)
                .extract().as(CreateTokenResponseModel.class);
    }

    @Step("Создаем новый токен без передачи данных по токену")
    public CreateTokenWithInvalidDataResponseModel createNewTokenWithNoBody(LoginResponseModel loginResponse) {
        return given(requestSpec)
                .header(AUTH_HEADER.value, BEARER_PREFIX.value + loginResponse.getToken())
                .when()
                .put("/tokens")
                .then()
                .spec(tokenCreateResponseSpecWithCode400)
                .extract().as(CreateTokenWithInvalidDataResponseModel.class);
    }

    @Step("Создаем новый токен с невалидной датой истечения срока действия токена")
    public CreateTokenWithInvalidDataResponseModel createNewTokenWithInvalidExpDate(
            LoginResponseModel loginResponse,
            CreateTokenWithInvalidExpDateBodyModel invalidExpTimeTokenData) {
        return given(requestSpec)
                .header(AUTH_HEADER.value, BEARER_PREFIX.value + loginResponse.getToken())
                .body(invalidExpTimeTokenData)
                .when()
                .put("/tokens")
                .then()
                .spec(tokenCreateResponseSpecWithCode400)
                .extract().as(CreateTokenWithInvalidDataResponseModel.class);
    }

    @Step("Удаляем ранее созданный токен")
    public DeleteTokenResponseModel deleteExistingToken(
            LoginResponseModel loginResponse,
            CreateTokenResponseModel deleteTokenResponse) {
        return given(requestSpec)
                .header(AUTH_HEADER.value, BEARER_PREFIX.value + loginResponse.getToken())
                .pathParam("id", deleteTokenResponse.getId())
                .when()
                .delete("/tokens/{id}")
                .then()
                .spec(tokenDeleteResponseSpecWithCode200)
                .extract().as(DeleteTokenResponseModel.class);
    }

    @Step("Удаляем несуществующий токен")
    public DeleteTokenResponseModel deleteNonExistingToken(
            LoginResponseModel loginResponse,
            int tokenId) {
        return given(requestSpec)
                .header(AUTH_HEADER.value, BEARER_PREFIX.value + loginResponse.getToken())
                .pathParam("id", tokenId)
                .when()
                .delete("/tokens/{id}")
                .then()
                .spec(tokenDeleteResponseSpecWithCode403)
                .extract().as(DeleteTokenResponseModel.class);
    }

    @Step("Удаляем токен без передачи его ID")
    public DeleteTokenResponseModel deleteTokenWithNoId(LoginResponseModel loginResponse) {
        return given(requestSpec)
                .header(AUTH_HEADER.value, BEARER_PREFIX.value + loginResponse.getToken())
                .when()
                .delete("/tokens/")
                .then()
                .spec(tokenDeleteResponseSpecWithCode404)
                .extract().as(DeleteTokenResponseModel.class);
    }

    @Step("Проверяем, что наименование токена в ответе соответствует наименованию в запросе")
    public void checkResponseTitle(
            CreateTokenBodyModel tokenRequestData,
            CreateTokenResponseModel tokenResponseData) {
        assertThat(tokenResponseData.getTitle()).isEqualTo(tokenRequestData.getTitle());
    }

    @Step("Проверяем, что в ответе отображается сообщение - {createTokenMsg}")
    public void checkCreateTokenMsg(
            CreateTokenWithInvalidDataResponseModel tokenResponseData,
            String createTokenMsg) {
        assertThat(tokenResponseData.getMessage()).isEqualTo(createTokenMsg);
    }

    @Step("Проверяем, что в ответе отображается сообщение - {deleteTokenMsg}")
    public void checkDeleteTokenMsg(
            DeleteTokenResponseModel deleteTokenResponse,
            String deleteTokenMsg) {
        assertThat(deleteTokenResponse.getMessage()).isEqualTo(deleteTokenMsg);
    }
}

