package ru.sibdigital.jopsd.model.enums;

public enum Types {
    EVENT(Long.valueOf(1)),
    CHECK_POINT(Long.valueOf(2)),
    RESULT_TYPE(Long.valueOf(3));

    private final Long value;
    private Types(Long value) {
        this.value = value;
    }

    public Long getValue() {
        return value;
    }
}
