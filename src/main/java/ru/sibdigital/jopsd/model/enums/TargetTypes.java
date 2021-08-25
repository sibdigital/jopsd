package ru.sibdigital.jopsd.model.enums;

public enum TargetTypes {
    PURPOSE(Long.valueOf(39)), // цель
    INDICATOR(Long.valueOf(40)), // показатель
    RESULT(Long.valueOf(41)); // результат

    private final Long value;
    private TargetTypes(Long value) {
        this.value = value;
    }

    public Long getValue() {
        return value;
    }
}
