package io.vikunja.pages.mobile;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static io.appium.java_client.AppiumBy.accessibilityId;

public class HomeScreen {
    private final SelenideElement appLogo = $(accessibilityId("Vikunja"));

    @Step("Проверяем, что логотип приложения отображается на домашнем экране")
    public void checkLogoIsVisible() {
        appLogo.shouldBe(visible);
    }
}
