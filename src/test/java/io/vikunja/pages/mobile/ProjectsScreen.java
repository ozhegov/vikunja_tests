package io.vikunja.pages.mobile;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static io.appium.java_client.AppiumBy.accessibilityId;

public class ProjectsScreen {
    @Step("Открываем проект {projectTitle}")
    public void openProject(String projectTitle) {
        $(accessibilityId(projectTitle)).click();
    }
}