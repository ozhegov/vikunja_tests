package io.vikunja.tests.mobile;

import io.qameta.allure.*;
import io.vikunja.helpers.annotations.WithMobileAuthLogin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

@Owner("Maksim Ozhegov")
@Epic("Редактирование задач")
@Feature("Редактирование задач в мобильном приложениии")
@Tags({@Tag("Mobile"), @Tag("Task")})
@DisplayName("Проверки функционирования редактирования задач")
public class TaskTests extends TestBase{

    @Test
    @WithMobileAuthLogin
    @Story("Изменение названия задачи")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("При изменении названия задачи новое название отображается в списке задач")
    public void newTaskTitleIsVisibleAfterEditing(){
        bottomNavigationBar
                .clickProjectsBtn();
        projectsScreen
                .openProject(createProjectResponse.getTitle());
        tasksScreen
                .clickEditBtn();
        editTaskScreen
                .setTaskTitle(data.taskTitleAdditional)
                .clickSaveBtnAfterTitleChange();
        tasksScreen
                .checkSuccessTaskEditMsgIsVisible()
                .checkNewTaskTitleIsVisible(createTaskResponse.getTitle() + data.taskTitleAdditional);
    }

    @Test
    @WithMobileAuthLogin
    @Story("Изменение приоритета задачи")
    @Severity(SeverityLevel.MINOR)
    @DisplayName("При изменении приоритета задачи новый приоритет отображается в списке задач")
    public void newPriorityIsVisibleAfterEditing(){
        bottomNavigationBar
                .clickProjectsBtn();
        projectsScreen
                .openProject(createProjectResponse.getTitle());
        tasksScreen
                .clickEditBtn();
        editTaskScreen
                .clickPrioritySelector()
                .selectPriority(data.taskPriority)
                .clickSaveBtn();
        tasksScreen
                .checkNewPriorityIsVisible(createTaskResponse.getTitle(), data.taskPriority);
    }

    @Test
    @WithMobileAuthLogin
    @Story("Удаление задачи")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("При удалении задачи она больше не отображается в списке задач")
    public void taskIsNotVisibleAfterDelete(){
        bottomNavigationBar
                .clickProjectsBtn();
        projectsScreen
                .openProject(createProjectResponse.getTitle());
        tasksScreen
                .clickEditBtn();
        editTaskScreen
                .clickDeleteBtn()
                .clickDeleteConfirmBtn();
        tasksScreen
                .clickBackButton();
        projectsScreen
                .openProject(createProjectResponse.getTitle());
        tasksScreen
                .checkEmptyTaskListMsgAppears();
    }
}
