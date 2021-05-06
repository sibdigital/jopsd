package ru.sibdigital.jopsd.model.enums;

public enum Statuses {
    NOT_STARTED(Long.valueOf(1)),
    IN_WORK(Long.valueOf(2)),
    FINISHED(Long.valueOf(3)),
    CANCELED(Long.valueOf(4)),
    POSTPONED(Long.valueOf(5)),
    ON_CHECK(Long.valueOf(6));

    private final Long value;
    private Statuses(Long value) {
        this.value = value;
    }

    public Long getValue() {
        return value;
    }
}
