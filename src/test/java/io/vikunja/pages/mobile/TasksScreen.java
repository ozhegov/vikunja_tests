package io.vikunja.pages.mobile;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import io.vikunja.data.mobile.MobileMessages;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static io.appium.java_client.AppiumBy.accessibilityId;
import static io.appium.java_client.AppiumBy.className;

public class TasksScreen {
    private final SelenideElement editTaskButton = $$(className("android.widget.Button")).get(2),
            successfulEditMsg = $(accessibilityId(MobileMessages.SUCCESS_MSG_TASK_UPDATE.message)),
            backButton = $(accessibilityId("Back")),
            emptyTaskListMsg = $(accessibilityId(MobileMessages.INFO_MSG_EMPTY_TASK_LIST.message));

    @Step("Переходим к редактированию задачи")
    public void clickEditBtn() {
        editTaskButton.click();
    }

    @Step("Проверяем, что на экране отображается сообщение о сохранении настроек")
    public TasksScreen checkSuccessTaskEditMsgIsVisible() {
        successfulEditMsg.shouldBe(visible);
        return this;
    }

    @Step("Проверяем, что на экране отображается задача с изменным именем - {taskTitle}")
    public void checkNewTaskTitleIsVisible(String taskTitle) {
        $(accessibilityId(taskTitle)).shouldBe(visible);
    }

    @Step("Проверяем, что на экране отображается задача {taskTitle} с изменным приоритетом - {priority}")
    public void checkNewPriorityIsVisible(String taskTitle, String priority) {
        $(accessibilityId(taskTitle + "\n !" + priority)).shouldBe(visible);
    }

    @Step("Возвращаемся к списку проектов")
    public void clickBackButton() {
        backButton.click();
    }

    @Step("Проверяем, что на экране отображается сообщение об отсутствии задач")
    public void checkEmptyTaskListMsgAppears() {
        emptyTaskListMsg.shouldBe(visible);
    }
}
