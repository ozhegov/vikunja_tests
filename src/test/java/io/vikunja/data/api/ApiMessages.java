package io.vikunja.data.api;

public enum ApiMessages {
    TOKEN_INVALID_EXP_DATE_MSG("Time.UnmarshalJSON: input is not a JSON string"),
    TOKEN_EMPTY_BODY_MSG("Struct is invalid. Invalid Data"),
    TOKEN_SUCCESS_DELETE_MSG("Successfully deleted."),
    TOKEN_FORBID_DELETE_MSG("Forbidden"),
    TOKEN_NO_ID_DELETE_MSG("Not Found"),
    LABEL_NOT_FOUND_ID_MSG("This label does not exist.");

    public final String message;

    ApiMessages(String message) {
        this.message = message;
    }
}
