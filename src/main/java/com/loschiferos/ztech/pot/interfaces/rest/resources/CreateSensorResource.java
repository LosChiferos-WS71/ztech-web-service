package com.loschiferos.ztech.pot.interfaces.rest.resources;

import com.loschiferos.ztech.pot.domain.model.valueobjects.SensorType;

public record CreateSensorResource(Long flowerpotId, int internalSerialNumber, SensorType type, int value) {
}
