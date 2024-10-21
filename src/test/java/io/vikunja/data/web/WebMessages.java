package io.vikunja.data.web;

public enum WebMessages {
    WELCOME_MSG_TEXT("Nothing to do — Have a nice day!"),
    VALIDATION_MSG_USERNAME_TEXT("A user with this username already exists."),
    VALIDATION_MSG_EMAIL_TEXT("A user with this email address already exists."),
    VALIDATION_HINT_SHORT_PASSWORD_TEXT("Password must have at least 8 characters."),
    VALIDATION_HINT_LONG_PASSWORD_TEXT("Password must have at most 72 characters.");

    public final String message;

    WebMessages(String message) {
        this.message = message;
    }
}
