package com.loschiferos.ztech.pot.domain.model.valueobjects;

public enum ParameterType {
    MIN_TEMPERATURE,
    MAX_TEMPERATURE,
    MIN_HUMIDITY,
    MAX_HUMIDITY,
    MIN_SUNLIGHT,
    MAX_SUNLIGHT;

    public static ParameterType fromValue(Long type) {
        return switch (type.intValue()) {
            case 0 -> MIN_TEMPERATURE;
            case 1 -> MAX_TEMPERATURE;
            case 2 -> MIN_HUMIDITY;
            case 3 -> MAX_HUMIDITY;
            case 4 -> MIN_SUNLIGHT;
            case 5 -> MAX_SUNLIGHT;
            default -> throw new IllegalArgumentException("Invalid type");
        };
    }
}
