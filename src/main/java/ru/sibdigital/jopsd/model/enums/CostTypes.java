package ru.sibdigital.jopsd.model.enums;

public enum CostTypes {
    FEDERAL_BUDGET(Long.valueOf(1)),
    REGIONAL_BUDGET(Long.valueOf(2)),
    EXTRABUDGETARY_FUNDS(Long.valueOf(3)),
    MUNICIPAL_BUDGET(Long.valueOf(4));

    private final Long value;
    private CostTypes(Long value) {
        this.value = value;
    }

    public Long getValue() {
        return value;
    }
}
