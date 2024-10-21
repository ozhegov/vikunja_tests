package io.vikunja.data.web;

public enum TaskPriority {
    LOW_PRIORITY("Low"),
    MEDIUM_PRIORITY("Medium"),
    HIGH_PRIORITY("High"),
    URGENT_PRIORITY("Urgent"),
    DO_NOW_PRIORITY("DO NOW");

    public final String priority;

    TaskPriority(String priority) {
        this.priority = priority;
    }
}
