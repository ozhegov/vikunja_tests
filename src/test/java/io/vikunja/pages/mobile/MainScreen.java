package io.vikunja.pages.mobile;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static io.appium.java_client.AppiumBy.accessibilityId;
import static io.appium.java_client.AppiumBy.className;

public class MainScreen {
    private final SelenideElement serverAddressInput = $$(className("android.widget.EditText")).get(0),
            serverAddressHint = $$(className("android.widget.EditText")).get(1),
            usernameInput = $$(className("android.widget.EditText")).get(1),
            passwordInput = $$(className("android.widget.EditText")).get(2),
            loginButton = $(accessibilityId("Login"));

    @Step("Открываем приложение")
    public void openApp() {
        open();
    }

    @Step("Вводим адрес сервера - {serverAddress}")
    public MainScreen setServerAddress(String serverAddress) {
        serverAddressInput.click();
        serverAddressInput.sendKeys(serverAddress);
        serverAddressHint.click();
        return this;
    }

    @Step("Вводим логин - {username}")
    public MainScreen setUsername(String username) {
        usernameInput.click();
        usernameInput.sendKeys(username);
        return this;
    }

    @Step("Вводим пароль - {password}")
    public MainScreen setPassword(String password) {
        passwordInput.click();
        passwordInput.sendKeys(password);
        return this;
    }

    @Step("Нажимаем на кнопку входа в аккаунт")
    public MainScreen clickLoginBtn() {
        loginButton.click();
        return this;
    }

    @Step("Проверяем, что на экране отображается сообщение об ошибке - {validationMessage}")
    public void checkValidationMsgIsVisible(String validationMessage) {
        $(accessibilityId(validationMessage)).shouldBe(visible);
    }
}
