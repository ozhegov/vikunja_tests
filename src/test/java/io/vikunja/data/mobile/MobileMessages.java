package io.vikunja.data.mobile;

public enum MobileMessages {
    VALIDATION_MSG_NO_USERNAME_LOGIN("Please specify a username and a password."),
    VALIDATION_MSG_WRONG_USERNAME_LOGIN("Wrong username or password."),
    SUCCESS_MSG_TASK_UPDATE("The task was updated successfully!"),
    INFO_MSG_EMPTY_TASK_LIST("This project is empty.");

    public final String message;

    MobileMessages(String message) {
        this.message = message;
    }
}
