package io.vikunja.tests.api;

import io.qameta.allure.*;
import io.vikunja.api.models.token.createToken.invalidData.CreateTokenWithInvalidDataResponseModel;
import io.vikunja.api.models.token.createToken.validData.CreateTokenResponseModel;
import io.vikunja.api.models.token.deleteToken.DeleteTokenResponseModel;
import io.vikunja.data.api.ApiMessages;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

@Owner("Maksim Ozhegov")
@Epic("Аутентификация пользователей")
@Feature("Аутентификация по API токену")
@Tags({@Tag("API"), @Tag("Token")})
@DisplayName("Проверки функционирования API токенов")
public class TokenTests extends TestBase {

    @Test
    @Story("Создание токена")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("При создании нового токена c валидными данными его название корректно отображается в ответе метода")
    public void sameTokenTitleVisibleInRequestAndResponse() {
        CreateTokenResponseModel tokenResponseData = tokenApi
                .createNewToken(loginResponse, data.validTokenData);
        tokenApi
                .checkResponseTitle(data.validTokenData, tokenResponseData);
    }

    @Test
    @Story("Создание токена")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("При создании нового токена без предоставления по нему необходимых данных в ответе метода отображается сообщение об ошибке")
    public void errorMsgIsVisibleAfterRequestWithNoBody() {
        CreateTokenWithInvalidDataResponseModel tokenInvalidResponseData = tokenApi
                .createNewTokenWithNoBody(loginResponse);
        tokenApi
                .checkCreateTokenMsg(tokenInvalidResponseData, ApiMessages.TOKEN_EMPTY_BODY_MSG.message);
    }

    @Test
    @Story("Создание токена")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("При создании нового токена с датой истечения в некорректном формате в ответе метода отображается сообщение об ошибке")
    public void errorMsgIsVisibleAfterRequestWithInvalidExpDate() {
        CreateTokenWithInvalidDataResponseModel tokenInvalidResponseData = tokenApi
                .createNewTokenWithInvalidExpDate(loginResponse, data.invalidExpTimeTokenData);
        tokenApi
                .checkCreateTokenMsg(tokenInvalidResponseData, ApiMessages.TOKEN_INVALID_EXP_DATE_MSG.message);
    }

    @Test
    @Story("Удаление токена")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("При удалении имеющегося у пользователя токена в ответе метода отображается сообщение об успешном удалении")
    public void successMsgIsVisibleAfterDeleteWithExistingId() {
        CreateTokenResponseModel tokenResponseData = tokenApi
                .createNewToken(loginResponse, data.validTokenData);
        DeleteTokenResponseModel deleteTokenResponse = tokenApi
                .deleteExistingToken(loginResponse, tokenResponseData);
        tokenApi
                .checkDeleteTokenMsg(deleteTokenResponse, ApiMessages.TOKEN_SUCCESS_DELETE_MSG.message);
    }

    @Test
    @Story("Удаление токена")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("При попытке удаления отсутствующего у пользователя токена в ответе метода отображается сообщение об ошибке")
    public void errorMsgIsVisibleAfterDeleteWithNonExistingId() {
        DeleteTokenResponseModel deleteTokenResponse = tokenApi
                .deleteNonExistingToken(loginResponse, data.nonExistingTokenId);
        tokenApi
                .checkDeleteTokenMsg(deleteTokenResponse, ApiMessages.TOKEN_FORBID_DELETE_MSG.message);
    }

    @Test
    @Story("Удаление токена")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("При попытке удаления токена без указания его ID в ответе метода отображается сообщение об ошибке")
    public void errorMsgIsVisibleAfterDeleteWithNoId() {
        DeleteTokenResponseModel deleteTokenResponse = tokenApi
                .deleteTokenWithNoId(loginResponse);
        tokenApi
                .checkDeleteTokenMsg(deleteTokenResponse, ApiMessages.TOKEN_NO_ID_DELETE_MSG.message);
    }
}
