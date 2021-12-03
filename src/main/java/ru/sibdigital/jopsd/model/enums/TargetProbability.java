package ru.sibdigital.jopsd.model.enums;

public enum TargetProbability {
    VERY_LOW(1), // очень низкая
    LOW(2), // низкая
    MEDIUM(3), // средняя
    HIGH(4),  // высокая
    VERY_HIGH(5); // очень высокая

    private final Integer value;
    private TargetProbability(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public static TargetProbability getTargetProbabilityByProbabilityValue(Integer value) {
        if (value <=10) {
            return TargetProbability.VERY_LOW;
        } else if (value <= 35) {
            return TargetProbability.LOW;
        } else if (value <= 65) {
            return TargetProbability.MEDIUM;
        } else if (value <= 90) {
            return TargetProbability.HIGH;
        } else if (value > 90) {
            return TargetProbability.VERY_HIGH;
        }
        return null;
    }
}
