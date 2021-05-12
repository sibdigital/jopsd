package ru.sibdigital.jopsd.model.enums;

public enum ProjectTypes {
    TYPE_PROJECT("project"),
    TYPE_TEMPLATE("template");

    private final String value;
    private ProjectTypes(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
