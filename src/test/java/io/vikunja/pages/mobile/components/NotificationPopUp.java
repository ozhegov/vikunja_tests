package io.vikunja.pages.mobile.components;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static io.appium.java_client.AppiumBy.id;

public class NotificationPopUp {
    private final SelenideElement doNotAllowBtn = $(id("com.android.permissioncontroller:id/permission_deny_button"));

    @Step("Закрываем нотификационный поп-ап")
    public void closeNotificationPopUp() {
        doNotAllowBtn.click();
    }
}
