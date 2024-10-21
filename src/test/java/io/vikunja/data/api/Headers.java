package io.vikunja.data.api;

public enum Headers {
    AUTH_HEADER("Authorization"),
    BEARER_PREFIX("Bearer ");

    public final String value;

    Headers(String value) {
        this.value = value;
    }
}
