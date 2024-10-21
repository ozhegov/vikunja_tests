package io.vikunja.tests.web;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import io.restassured.RestAssured;
import io.vikunja.api.*;
import io.vikunja.api.models.login.LoginResponseModel;
import io.vikunja.drivers.ConfigDriver;
import io.vikunja.helpers.Attachments;
import io.vikunja.pages.web.GeneralSettingsPage;
import io.vikunja.pages.web.HomePage;
import io.vikunja.pages.web.RegistrationPage;
import io.vikunja.pages.web.components.SearchModal;
import io.vikunja.pages.web.components.TopBar;
import io.vikunja.utils.TestData;
import lombok.Setter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class TestBase {
    protected final TestData data = new TestData();
    protected final RegistrationApi registrationApi = new RegistrationApi();
    protected final RegistrationPage registrationPage = new RegistrationPage();
    protected final ProjectApi projectApi = new ProjectApi();
    protected final TaskApi taskApi = new TaskApi();
    protected final TeamApi teamApi = new TeamApi();
    protected final LabelApi labelApi = new LabelApi();
    protected final HomePage homePage = new HomePage();
    protected final GeneralSettingsPage generalSettingsPage = new GeneralSettingsPage();
    protected final TopBar topBar = new TopBar();
    protected final SearchModal searchModal = new SearchModal();
    @Setter
    protected static LoginResponseModel loginResponse;

    @BeforeAll
    static void configuration() {
        Configuration.baseUrl = ConfigDriver.getWebConfig().baseUrl();
        RestAssured.baseURI = ConfigDriver.getWebConfig().baseURI();
        RestAssured.basePath = ConfigDriver.getWebConfig().basePath();
        Configuration.browser = ConfigDriver.getWebConfig().browser();
        Configuration.browserSize = ConfigDriver.getWebConfig().browserSize();
        Configuration.browserVersion = ConfigDriver.getWebConfig().browserVersion();

        String remote = ConfigDriver.getWebConfig().remoteHost();
        String selenoidUsername = ConfigDriver.getAuthConfig().selenoidUsername();
        String selenoidPassword = ConfigDriver.getAuthConfig().selenoidPassword();
        if (remote != null) {
            Configuration.remote = String.format("https://%s:%s@%s/wd/hub",
                    selenoidUsername, selenoidPassword, remote);
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                    "enableVNC", true,
                    "enableVideo", true
            ));
            Configuration.browserCapabilities = capabilities;
        }
    }

    @BeforeEach
    public void beforeEach() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @AfterEach
    public void after() {
        Attachments.screenshotAs("Screenshot");
        Attachments.pageSource();
        Attachments.browserConsoleLogs();
        Attachments.addVideo();
        closeWebDriver();
    }
}
