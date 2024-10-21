package io.vikunja.tests.api;

import io.restassured.RestAssured;
import io.vikunja.api.*;
import io.vikunja.api.models.login.LoginResponseModel;
import io.vikunja.drivers.ConfigDriver;
import io.vikunja.utils.TestData;
import org.junit.jupiter.api.BeforeAll;

public class TestBase {
    protected final TestData data = new TestData();
    protected final LoginApi loginApi = new LoginApi();
    protected final LabelApi labelApi = new LabelApi();
    protected final TokenApi tokenApi = new TokenApi();
    protected final ProjectApi projectApi = new ProjectApi();
    protected final TaskApi taskApi = new TaskApi();
    protected final LoginResponseModel loginResponse = loginApi.login(data.credentials);

    @BeforeAll
    static void configuration() {
        RestAssured.baseURI = ConfigDriver.getWebConfig().baseURI();
        RestAssured.basePath = ConfigDriver.getWebConfig().basePath();
    }
}
