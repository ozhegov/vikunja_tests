package io.vikunja.tests.web;

import io.qameta.allure.*;
import io.vikunja.helpers.annotations.WithLogin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@Owner("Maksim Ozhegov")
@Epic("Настройки приложения")
@Feature("Управление основными настройками приложения")
@Tags({@Tag("Web"), @Tag("GeneralSettings")})
@DisplayName("Проверки функционирования основных настроек приложения")
public class GeneralSettingsTests extends TestBase {

    @Test
    @WithLogin
    @Story("Изменение имени пользователя")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("При изменении имени пользователя в настройках новое имя отображается в профиле")
    public void newNameVisibleAfterChangeInSettings() {
        generalSettingsPage
                .openGeneralSettingsPage()
                .clearMyNameInput()
                .setName(data.validUsername)
                .removeBanner()
                .clickSaveBtn();
        topBar
                .checkUsername(data.validUsername);
    }

    @WithLogin
    @DisplayName("При изменении языка в настройках приложение отображается на выбранном языке - ")
    @ParameterizedTest(name = "{0}")
    @CsvSource(value = {
            "Русский, Основные настройки",
            "Deutsch, Allgemeine Einstellungen",
            "Italiano, Impostazioni Generali",
            "中文, 通用设置"
    })
    @Story("Изменение языка приложения")
    @Severity(SeverityLevel.NORMAL)
    public void newLanguageVisibleAfterChangeInSettings(String language, String settingsName) {
        generalSettingsPage
                .openGeneralSettingsPage()
                .selectLanguage(language)
                .removeBanner()
                .clickSaveBtn()
                .checkSettingsNameLanguage(settingsName);
    }

    @WithLogin
    @DisplayName("При изменении темы приложения в настройках цвет приложения меняется в соответствии с выбранной темой - ")
    @ParameterizedTest(name = "{0}")
    @CsvSource(value = {
            "Light | rgba(255, 255, 255, 1)",
            "Dark | rgba(17, 24, 39, 1)"
    }, delimiter = '|')
    @Story("Изменение темы приложения")
    @Severity(SeverityLevel.MINOR)
    public void correctColorVisibleAfterChangeInSettings(String colorScheme, String color) {
        generalSettingsPage
                .openGeneralSettingsPage()
                .selectColorScheme(colorScheme)
                .removeBanner()
                .clickSaveBtn()
                .checkColorScheme(color);
    }

}