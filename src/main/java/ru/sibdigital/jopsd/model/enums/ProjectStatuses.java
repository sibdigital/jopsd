package ru.sibdigital.jopsd.model.enums;

public enum ProjectStatuses {
    STATUS_ACTIVE(Long.valueOf(1)),
    STATUS_ARCHIVED(Long.valueOf(9));

    private final Long value;
    private ProjectStatuses(Long value) {
        this.value = value;
    }

    public Long getValue() {
        return value;
    }
}
