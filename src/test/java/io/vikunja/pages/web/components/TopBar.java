package io.vikunja.pages.web.components;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class TopBar {
    private final SelenideElement
            username = $(".username"),
            searchIcon = $(".navbar-end .base-button");

    @Step("Открываем модальное окно поиска")
    public void clickSearchIcon() {
        searchIcon.click();
    }

    @Step("Проверяем, что в верхнем меню отображается имя пользователя - {name}")
    public void checkUsername(String name) {
        username.shouldHave(text(name));
    }
}
