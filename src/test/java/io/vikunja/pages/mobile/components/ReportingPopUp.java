package io.vikunja.pages.mobile.components;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static io.appium.java_client.AppiumBy.accessibilityId;

public class ReportingPopUp {
    private final SelenideElement noBtn = $(accessibilityId("No"));

    @Step("Закрываем поп-ап отправки данных")
    public void closeReportingPopUp() {
        noBtn.click();
    }
}
