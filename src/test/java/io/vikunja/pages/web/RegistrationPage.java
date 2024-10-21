package io.vikunja.pages.web;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.disabled;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class RegistrationPage {
    private final SelenideElement
            usernameInput = $("#username"),
            emailInput = $("#email"),
            passwordInput = $("#password"),
            createAccountBtn = $("#register-submit"),
            validationMsg = $(".message.danger"),
            validationPasswordHint = $(".help.is-danger");

    @Step("Открываем страницу регистрации")
    public RegistrationPage openRegistrationPage() {
        open("/register");
        return this;
    }

    @Step("Вводим логин - {username}")
    public RegistrationPage setUsername(String username) {
        usernameInput.setValue(username);
        return this;
    }

    @Step("Вводим email - {email}")
    public RegistrationPage setEmail(String email) {
        emailInput.setValue(email);
        return this;
    }

    @Step("Вводим пароль - {password}")
    public RegistrationPage setPassword(String password) {
        passwordInput.setValue(password);
        return this;
    }

    @Step("Нажимаем на кнопку создания аккаунта")
    public RegistrationPage clickCreateAccountBtn() {
        createAccountBtn.click();
        return this;
    }

    @Step("Убираем фокус с поля ввода password")
    public RegistrationPage unfocusPasswordInput() {
        passwordInput.unfocus();
        return this;
    }

    @Step("Проверяем, что на странице отображается сообщение - {validationMessage}")
    public void checkValidationMsgAppears(String validationMessage) {
        validationMsg.shouldHave(text(validationMessage));
    }

    @Step("Проверяем, что под полем password отображается подсказка - {validationHint}")
    public RegistrationPage checkPasswordValidationHintAppears(String validationHint) {
        validationPasswordHint.shouldHave(text(validationHint));
        return this;
    }

    @Step("Проверяем, что кнопка создания аккаунта задизейблена")
    public void checkCreateBtnDisabled() {
        createAccountBtn.shouldBe(disabled);
    }
}
