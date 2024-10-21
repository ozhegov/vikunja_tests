package io.vikunja.pages.web;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class HomePage {
    private final SelenideElement welcomeMsg = $(".has-text-centered.mt-6");

    @Step("Открываем домашнюю страницу")
    public void openHomePage() {
        open("/");
    }

    @Step("Проверяем, что на домашней странице отображается сообщение - {welcomeMessage} {username}")
    public void checkWelcomeMsgIsVisible(String welcomeMessage) {
        welcomeMsg.shouldHave(text(welcomeMessage));
    }
}
