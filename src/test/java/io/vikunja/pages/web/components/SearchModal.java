package io.vikunja.pages.web.components;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class SearchModal {
    private final SelenideElement
            searchInput = $(".action-input .input"),
            searchResult = $(".result-item-button"),
            searchTasksResult = $(".result-items .task"),
            searchLabelResult = $(".tag span");

    @Step("В поисковую строку вводим символ '+' и название проекта - {projectName}")
    public SearchModal setPlusAndProjectName(String projectName) {
        searchInput.setValue("+" + projectName);
        return this;
    }

    @Step("В поисковую строку вводим символ '@' и название команды - {teamName}")
    public SearchModal setAtAndTeamName(String teamName) {
        searchInput.setValue("@" + teamName);
        return this;
    }

    @Step("В поисковую строку вводим символ '*' и название лейбла - {labelName}")
    public SearchModal setAsteriskAndLabelName(String labelName) {
        searchInput.setValue("*" + "(" + labelName + ")");
        return this;
    }

    @Step("Проверяем, что в результатах поиска отображается искомое название - {searchValue}")
    public SearchModal checkSearchValueAppears(String searchValue) {
        searchResult.shouldHave(text(searchValue));
        return this;
    }

    @Step("Проверяем, что в результатах поиска отображается название созданной в рамках этого проекта задачи - {taskName}")
    public void checkTaskNameAppears(String taskName) {
        searchTasksResult.shouldHave(text(taskName));
    }

    @Step("Проверяем, что в результатах поиска отображается название искомого лейбла - {labelName}")
    public void checkLabelNameAppears(String labelName) {
        searchLabelResult.shouldHave(text(labelName));
    }
}
