package io.vikunja.helpers.extensions;

import io.vikunja.api.LoginApi;
import io.vikunja.api.ProjectApi;
import io.vikunja.api.RegistrationApi;
import io.vikunja.api.TaskApi;
import io.vikunja.api.models.login.LoginBodyModel;
import io.vikunja.api.models.login.LoginResponseModel;
import io.vikunja.api.models.project.CreateProjectResponseModel;
import io.vikunja.api.models.task.CreateTaskResponseModel;
import io.vikunja.drivers.ConfigDriver;
import io.vikunja.pages.mobile.MainScreen;
import io.vikunja.pages.mobile.components.NotificationPopUp;
import io.vikunja.pages.mobile.components.ReportingPopUp;
import io.vikunja.tests.mobile.TaskTests;
import io.vikunja.utils.TestData;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class MobileAuthLoginExtension implements BeforeEachCallback {
    private final TestData data = new TestData();
    private final RegistrationApi registrationApi = new RegistrationApi();
    private final LoginApi loginApi = new LoginApi();
    private final ProjectApi projectApi = new ProjectApi();
    private final TaskApi taskApi = new TaskApi();
    private final MainScreen mainScreen = new MainScreen();
    private final NotificationPopUp notificationPopUp = new NotificationPopUp();
    private final ReportingPopUp reportingPopUp = new ReportingPopUp();
    private static final String deviceHost = ConfigDriver.getMobileSystemConfig().deviceHost();
    final boolean isNotBrowserStack = !deviceHost.equals("browserstack");

    @Override
    public void beforeEach(ExtensionContext extensionContext) {
        registrationApi.registration(data.regData);
        LoginBodyModel credentials = LoginBodyModel.builder()
                .password(data.regData.getPassword())
                .username(data.regData.getUsername())
                .build();
        LoginResponseModel loginResponse = loginApi
                .login(credentials);
        CreateProjectResponseModel createProjectResponse = projectApi
                .createNewProject(loginResponse, data.projectData);
        CreateTaskResponseModel createTaskResponse = taskApi
                .createNewTask(loginResponse, data.taskData, createProjectResponse);

        mainScreen
                .openApp();
        if (isNotBrowserStack) {
            notificationPopUp
                    .closeNotificationPopUp();
        }
        reportingPopUp
                .closeReportingPopUp();
        mainScreen
                .setServerAddress(data.existServerAddress)
                .setUsername(credentials.getUsername())
                .setPassword(credentials.getPassword())
                .clickLoginBtn();

        TaskTests.setCreateProjectResponse(createProjectResponse);
        TaskTests.setCreateTaskResponse(createTaskResponse);
    }
}
