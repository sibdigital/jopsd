package ru.sibdigital.jopsd.model.enums;

public enum WorkPackageProblemStatuses {
    CREATED("created"),
    SOLVED("solved");

    private final String value;
    private WorkPackageProblemStatuses(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
