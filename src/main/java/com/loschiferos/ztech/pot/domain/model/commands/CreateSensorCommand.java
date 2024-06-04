package com.loschiferos.ztech.pot.domain.model.commands;

import com.loschiferos.ztech.pot.domain.model.valueobjects.SensorType;

public record CreateSensorCommand(Long flowerpotId, int internalSerialNumber, SensorType type, int value) {
}
