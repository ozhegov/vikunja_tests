package io.vikunja.tests.web;

import io.qameta.allure.*;
import io.vikunja.api.models.registration.RegistrationResponseModel;
import io.vikunja.data.web.WebMessages;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

@Owner("Maksim Ozhegov")
@Epic("Регистрация пользователей")
@Feature("Заполнение регистрационной формы")
@Tags({@Tag("Web"), @Tag("Registration")})
@DisplayName("Проверки заполнения формы для регистрации пользователей")
public class RegistrationTests extends TestBase {

    @Test
    @Story("Регистрация пользователей с заполненными валидными регистрационными данными")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("При регистрации с валидными данными пользователю отображается приветственное сообщение")
    public void welcomeMessageVisibleAfterRegistrationWithValidData() {
        registrationPage
                .openRegistrationPage()
                .setUsername(data.validUsername)
                .setEmail(data.validEmail)
                .setPassword(data.validPassword)
                .clickCreateAccountBtn();
        homePage
                .checkWelcomeMsgIsVisible(
                        WebMessages
                                .WELCOME_MSG_TEXT
                                .message);
    }

    @Test
    @Story("Регистрация пользователей с использованием неуникальных регистрационных данных")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("При попытке регистрации с уже используемым логином отображается сообщение об ошибке и регистрация не происходит")
    public void errorMsgVisibleAfterRegistrationWithNonUniqueUsername() {
        RegistrationResponseModel registrationResponse = registrationApi
                .registration(data.regData);
        registrationPage
                .openRegistrationPage()
                .setUsername(registrationResponse.getUsername())
                .setEmail(data.validEmail)
                .setPassword(data.validPassword)
                .clickCreateAccountBtn()
                .checkValidationMsgAppears(
                        WebMessages
                                .VALIDATION_MSG_USERNAME_TEXT
                                .message);
    }

    @Test
    @Story("Регистрация пользователей с использованием неуникальных регистрационных данных")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("При попытке регистрации с уже используемым email отображается сообщение об ошибке и регистрация не происходит")
    public void errorMsgVisibleAfterRegistrationWithNonUniqueEmail() {
        RegistrationResponseModel registrationResponse = registrationApi
                .registration(data.regData);
        registrationPage
                .openRegistrationPage()
                .setUsername(data.validUsername)
                .setEmail(registrationResponse.getEmail())
                .setPassword(data.validPassword)
                .clickCreateAccountBtn()
                .checkValidationMsgAppears(
                        WebMessages
                                .VALIDATION_MSG_EMAIL_TEXT
                                .message);
    }

    @Test
    @Story("Регистрация пользователей с использованием невалидных регистрационных данных")
    @Severity(SeverityLevel.MINOR)
    @DisplayName("При попытке регистрации с коротким паролем отображается сообщение о требуемой длине пароля и регистрация не происходит")
    public void errorMsgVisibleAfterRegistrationWithInvalidShortPassword() {
        registrationPage
                .openRegistrationPage()
                .setUsername(data.validUsername)
                .setEmail(data.validEmail)
                .setPassword(data.invalidShortPassword)
                .unfocusPasswordInput()
                .checkPasswordValidationHintAppears(
                        WebMessages
                                .VALIDATION_HINT_SHORT_PASSWORD_TEXT
                                .message)
                .checkCreateBtnDisabled();
    }

    @Test
    @Story("Регистрация пользователей с использованием невалидных регистрационных данных")
    @Severity(SeverityLevel.MINOR)
    @DisplayName("При попытке регистрации с длинным паролем отображается сообщение о требуемой длине пароля и регистрация не происходит")
    public void errorMsgVisibleAfterRegistrationWithInvalidPassword() {
        registrationPage
                .openRegistrationPage()
                .setUsername(data.validUsername)
                .setEmail(data.validEmail)
                .setPassword(data.invalidLongPassword)
                .unfocusPasswordInput()
                .checkPasswordValidationHintAppears(
                        WebMessages
                                .VALIDATION_HINT_LONG_PASSWORD_TEXT
                                .message)
                .checkCreateBtnDisabled();
    }
}
