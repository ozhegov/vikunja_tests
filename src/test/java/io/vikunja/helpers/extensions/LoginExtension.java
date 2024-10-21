package io.vikunja.helpers.extensions;

import io.vikunja.api.LoginApi;
import io.vikunja.api.models.login.LoginResponseModel;
import io.vikunja.utils.TestData;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import static com.codeborne.selenide.Selenide.localStorage;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

public class LoginExtension implements BeforeEachCallback {
    private final TestData data = new TestData();
    private final LoginApi loginApi = new LoginApi();

    @Override
    public void beforeEach(ExtensionContext extensionContext) {
        LoginResponseModel loginResponse = loginApi.login(data.credentials);

        step("Применяем авторизационный токен", () -> {
            open("/favicon.ico");
            localStorage().setItem("token", loginResponse.getToken());
        });
    }
}
