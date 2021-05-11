package ru.sibdigital.jopsd.model.enums;

public enum WorkPackageProblemTypes {
    RISK("risk"),
    PROBLEM("problem");

    private final String value;
    private WorkPackageProblemTypes(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
