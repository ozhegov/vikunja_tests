package io.vikunja.tests.mobile;

import io.qameta.allure.*;
import io.vikunja.data.mobile.MobileMessages;
import org.junit.jupiter.api.*;

@Owner("Maksim Ozhegov")
@Epic("Авторизация пользователей")
@Feature("Авторизация в мобильном приложениии")
@Tags({@Tag("Mobile"), @Tag("Login")})
@DisplayName("Проверки заполнения формы для авторизации пользователей")
public class LoginTests extends TestBase {

    @Test
    @Story("Авторизация пользователей с заполненными валидными регистрационными данными")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("При авторизации с валидными данными пользователю отображается домашний экран")
    public void homeScreenIsOpenedAfterSuccessfulLogin() {
        mainScreen
                .setServerAddress(data.existServerAddress)
                .setUsername(data.existUsername)
                .setPassword(data.existPassword)
                .clickLoginBtn();
        homeScreen
                .checkLogoIsVisible();
        bottomNavigationBar
                .checkHomeBtnIsVisible()
                .checkProjectsBtnIsVisible()
                .checkSettingsBtnIsVisible();
    }

    @Test
    @Story("Авторизация пользователей с использованием невалидных авторизационных данных")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("При попытке авторизации без заполнения логина и пароля на экране отображается сообщение об ошибке и авторизация не происходит")
    public void errorMsgVisibleAfterLoginWithoutUsernameAndPass() {
        mainScreen
                .setServerAddress(data.existServerAddress)
                .clickLoginBtn()
                .checkValidationMsgIsVisible(
                        MobileMessages
                                .VALIDATION_MSG_NO_USERNAME_LOGIN
                                .message);
    }

    @Test
    @Story("Авторизация пользователей с использованием невалидных авторизационных данных")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("При попытке авторизации без заполнения пароля на экране отображается сообщение об ошибке и авторизация не происходит")
    public void errorMsgVisibleAfterLoginWithoutPass() {
        mainScreen
                .setServerAddress(data.existServerAddress)
                .setUsername(data.existUsername)
                .clickLoginBtn()
                .checkValidationMsgIsVisible(
                        MobileMessages
                                .VALIDATION_MSG_NO_USERNAME_LOGIN
                                .message);
    }

    @Test
    @Story("Авторизация пользователей с использованием невалидных авторизационных данных")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("При попытке авторизации с некорректным паролем на экране отображается сообщение об ошибке и авторизация не происходит")
    public void errorMsgVisibleAfterLoginWithValidUsernameAndUnregisteredPass() {
        mainScreen
                .setServerAddress(data.existServerAddress)
                .setUsername(data.existUsername)
                .setPassword(data.unregisteredPassword)
                .clickLoginBtn()
                .checkValidationMsgIsVisible(
                        MobileMessages
                                .VALIDATION_MSG_WRONG_USERNAME_LOGIN
                                .message);
    }

    @Test
    @Story("Авторизация пользователей с использованием невалидных авторизационных данных")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("При попытке авторизации с некорректными логином и паролем на экране отображается сообщение об ошибке и авторизация не происходит")
    public void errorMsgVisibleAfterLoginWithUnregisteredUsernameAndPass() {
        mainScreen
                .setServerAddress(data.existServerAddress)
                .setUsername(data.unregisteredUsername)
                .setPassword(data.unregisteredPassword)
                .clickLoginBtn()
                .checkValidationMsgIsVisible(
                        MobileMessages
                                .VALIDATION_MSG_WRONG_USERNAME_LOGIN
                                .message);
    }
}
