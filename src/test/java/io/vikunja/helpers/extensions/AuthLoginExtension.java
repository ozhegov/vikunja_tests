package io.vikunja.helpers.extensions;

import io.vikunja.api.LoginApi;
import io.vikunja.api.RegistrationApi;
import io.vikunja.api.models.login.LoginBodyModel;
import io.vikunja.api.models.login.LoginResponseModel;
import io.vikunja.tests.web.SearchTests;
import io.vikunja.utils.TestData;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import static com.codeborne.selenide.Selenide.localStorage;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

public class AuthLoginExtension implements BeforeEachCallback {
    private final TestData data = new TestData();
    private final RegistrationApi registrationApi = new RegistrationApi();
    private final LoginApi loginApi = new LoginApi();

    @Override
    public void beforeEach(ExtensionContext extensionContext) {
        registrationApi.registration(data.regData);
        LoginBodyModel credentials = LoginBodyModel.builder()
                .password(data.regData.getPassword())
                .username(data.regData.getUsername())
                .build();
        LoginResponseModel loginResponse = loginApi.login(credentials);

        step("Применяем авторизационный токен", () -> {
            open("/favicon.ico");
            localStorage().setItem("token", loginResponse.getToken());
        });

        SearchTests.setLoginResponse(loginResponse);
    }
}
