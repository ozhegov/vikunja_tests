package io.vikunja.pages.mobile;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static io.appium.java_client.AppiumBy.accessibilityId;
import static io.appium.java_client.AppiumBy.className;

public class EditTaskScreen {
    private final SelenideElement titleInput = $$(className("android.widget.EditText")).get(0),
            saveButton = $$(className("android.widget.Button")).get(6),
            saveButtonTitleChange = $$(className("android.widget.Button")).get(3),
            prioritySelector = $$(className("android.widget.Button")).get(3),
            deleteButton = $$(className("android.widget.Button")).get(1),
            deleteConfirmButton = $(accessibilityId("Delete"));

    @Step("Добавляем к названию задачи новое значение - {addTitleValue}")
    public EditTaskScreen setTaskTitle(String addTitleValue) {
        titleInput.click();
        titleInput.sendKeys(addTitleValue);
        return this;
    }

    @Step("Сохраняем изменения после изменения названия задачи")
    public void clickSaveBtnAfterTitleChange() {
        saveButtonTitleChange.click();
    }

    @Step("Переходим к выбору приоритета задачи")
    public EditTaskScreen clickPrioritySelector() {
        prioritySelector.click();
        return this;
    }

    @Step("Задаем приоритет задачи - {priority}")
    public EditTaskScreen selectPriority(String priority) {
        $(accessibilityId(priority)).click();
        return this;
    }

    @Step("Сохраняем изменения")
    public void clickSaveBtn() {
        saveButton.click();
    }

    @Step("Удаляем задачу")
    public EditTaskScreen clickDeleteBtn() {
        deleteButton.click();
        return this;
    }

    @Step("Подтверждаем удаление")
    public void clickDeleteConfirmBtn() {
        deleteConfirmButton.click();
    }
}
