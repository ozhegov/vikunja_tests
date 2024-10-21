package io.vikunja.pages.web;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.cssValue;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class GeneralSettingsPage {
    private final SelenideElement
            myNameInput = $("[placeholder='The new name']"),
            languageSelector = $("[value=cs-CZ]").parent(),
            colorSchemeSelector = $("[value=light]").parent(),
            saveBtn = $(".button"),
            generalSettingsName = $(".card-header-title"),
            generalSettingsSelector = $("select");

    @Step("Открываем страницу настроек")
    public GeneralSettingsPage openGeneralSettingsPage() {
        open("/user/settings/general");
        return this;
    }

    @Step("Очищаем содержимое поля 'My Name'")
    public GeneralSettingsPage clearMyNameInput() {
        myNameInput.clear();
        return this;
    }

    @Step("Вводим новое имя - {name}")
    public GeneralSettingsPage setName(String name) {
        myNameInput.setValue(name);
        return this;
    }

    @Step("Выбираем язык - {language}")
    public GeneralSettingsPage selectLanguage(String language) {
        languageSelector.selectOption(language);
        return this;
    }

    @Step("Выбираем тему приложения - {colorTheme}")
    public GeneralSettingsPage selectColorScheme(String colorTheme) {
        colorSchemeSelector.selectOption(colorTheme);
        return this;
    }

    @Step("Закрываем всплывающий баннер")
    public GeneralSettingsPage removeBanner() {
        executeJavaScript("document.querySelector('.demo-mode-banner').remove();");
        return this;
    }

    @Step("Сохраняем настройки")
    public GeneralSettingsPage clickSaveBtn() {
        saveBtn.scrollTo().click();
        return this;
    }

    @Step("Проверяем, что наименование раздела отображается на выбранном языке - {settingsName}")
    public void checkSettingsNameLanguage(String settingsName) {
        generalSettingsName.shouldHave(text(settingsName));
    }

    @Step("Проверяем, что цвет фона приложения соответствует выбранной теме")
    public void checkColorScheme(String color) {
        generalSettingsSelector.shouldHave(cssValue("background-color", color));
    }
}
