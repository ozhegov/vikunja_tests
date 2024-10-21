package io.vikunja.pages.mobile.components;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static io.appium.java_client.AppiumBy.accessibilityId;

public class BottomNavigationBar {
    private final SelenideElement homeButton = $(accessibilityId("Home\nTab 1 of 3")),
            projectsButton = $(accessibilityId("Projects\nTab 2 of 3")),
            settingsButton = $(accessibilityId("Settings\nTab 3 of 3"));

    @Step("По кнопке 'Projects' переходим к списку проектов")
    public void clickProjectsBtn() {
        projectsButton.click();
    }

    @Step("Проверяем, что кнопка 'Home' отображается на экране")
    public BottomNavigationBar checkHomeBtnIsVisible() {
        homeButton.shouldBe(visible);
        return this;
    }

    @Step("Проверяем, что кнопка 'Projects' отображается на экране")
    public BottomNavigationBar checkProjectsBtnIsVisible() {
        projectsButton.shouldBe(visible);
        return this;
    }

    @Step("Проверяем, что кнопка 'Settings' отображается на экране")
    public void checkSettingsBtnIsVisible() {
        settingsButton.shouldBe(visible);
    }
}
