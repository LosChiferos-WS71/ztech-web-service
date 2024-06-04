package com.loschiferos.ztech.pot.domain.model.valueobjects;

import com.loschiferos.ztech.shared.domain.exceptions.ValidationException;

public enum SensorType {
    TEMPERATURE,
    HUMIDITY,
    SUNLIGHT;

    public static SensorType fromValue(Long type) {
        return switch (type.intValue()) {
            case 0 -> TEMPERATURE;
            case 1 -> HUMIDITY;
            case 2 -> SUNLIGHT;
            default -> throw new ValidationException("Invalid type");
        };
    }
}
