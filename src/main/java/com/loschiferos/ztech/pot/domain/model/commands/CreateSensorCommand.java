package com.loschiferos.ztech.pot.domain.model.commands;

import com.loschiferos.ztech.pot.domain.model.valueobjects.SensorType;

public record CreateSensorCommand(Long flowerpotId, Double internalSerialNumber, Long type, Double value) {
}
