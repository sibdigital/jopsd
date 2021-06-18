package ru.sibdigital.jopsd.model.enums;

public enum TargetStatuses {
    COMPLETED_WITHOUT_DEVIATIONS(Long.valueOf(32)),
    COMPLETED_WITH_DEVIATIONS(Long.valueOf(33)),
    IS_PERFORMING_WITHOUT_DEVIATIONS(Long.valueOf(34)),
    IS_PERFORMING_WITH_DEVIATIONS(Long.valueOf(35)),
    IS_PERFORMING_WITH_CRITICAL_DEVIATIONS(Long.valueOf(36)),
    NO_INFORMATION(Long.valueOf(37)),
    FORECAST_INFORMATION(Long.valueOf(38));

    private final Long value;
    private TargetStatuses(Long value) {
        this.value = value;
    }

    public Long getValue() {
        return value;
    }
}
