package io.vikunja.tests.mobile;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import io.restassured.RestAssured;
import io.vikunja.api.models.project.CreateProjectResponseModel;
import io.vikunja.api.models.task.CreateTaskResponseModel;
import io.vikunja.drivers.BrowserstackDriver;
import io.vikunja.drivers.ConfigDriver;
import io.vikunja.drivers.EmulationDriver;
import io.vikunja.drivers.RealDeviceDriver;
import io.vikunja.helpers.Attachments;
import io.vikunja.pages.mobile.*;
import io.vikunja.pages.mobile.components.BottomNavigationBar;
import io.vikunja.pages.mobile.components.NotificationPopUp;
import io.vikunja.pages.mobile.components.ReportingPopUp;
import io.vikunja.utils.TestData;
import lombok.Setter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class TestBase {
    protected final TestData data = new TestData();
    protected final NotificationPopUp notificationPopUp = new NotificationPopUp();
    protected final ReportingPopUp reportingPopUp = new ReportingPopUp();
    protected final MainScreen mainScreen = new MainScreen();
    protected final HomeScreen homeScreen = new HomeScreen();
    protected final BottomNavigationBar bottomNavigationBar = new BottomNavigationBar();
    protected final ProjectsScreen projectsScreen = new ProjectsScreen();
    protected final TasksScreen tasksScreen = new TasksScreen();
    protected final EditTaskScreen editTaskScreen = new EditTaskScreen();
    @Setter
    protected static CreateProjectResponseModel createProjectResponse;
    @Setter
    protected static CreateTaskResponseModel createTaskResponse;
    private static final String deviceHost = ConfigDriver.getMobileSystemConfig().deviceHost();

    @BeforeAll
    static void setUp() {
        RestAssured.baseURI = ConfigDriver.getWebConfig().baseURI();
        RestAssured.basePath = ConfigDriver.getWebConfig().basePath();
        switch (deviceHost) {
            case "browserstack" -> Configuration.browser = BrowserstackDriver.class.getName();
            case "emulation" -> Configuration.browser = EmulationDriver.class.getName();
            case "real" -> Configuration.browser = RealDeviceDriver.class.getName();
        }
        Configuration.browserSize = null;
        Configuration.timeout = 30000;
    }

    @BeforeEach
    void beforeEach() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());

        boolean isNotBrowserStack = !deviceHost.equals("browserstack");
        boolean isNotTaskTests = !this.getClass().equals(TaskTests.class);

        if (isNotBrowserStack && isNotTaskTests) {
            mainScreen.openApp();
            notificationPopUp.closeNotificationPopUp();
            reportingPopUp.closeReportingPopUp();
        } else if (!isNotBrowserStack && isNotTaskTests) {
            mainScreen.openApp();
            reportingPopUp.closeReportingPopUp();
        }
    }

    @AfterEach
    void addAttachments() {
        Attachments.pageSource();
        switch (deviceHost) {
            case "browserstack": {
                String sessionId = Selenide.sessionId().toString();
                closeWebDriver();
                Attachments.addBrowserstackVideo(sessionId);
                break;
            }
            case "emulation", "real": {
                Attachments.screenshotAs("Last screenshot");
                closeWebDriver();
            }
        }
    }
}
