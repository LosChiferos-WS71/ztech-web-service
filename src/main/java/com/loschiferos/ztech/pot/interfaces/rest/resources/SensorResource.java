package com.loschiferos.ztech.pot.interfaces.rest.resources;

import com.loschiferos.ztech.pot.domain.model.valueobjects.SensorType;

import java.sql.Timestamp;

public record SensorResource(SensorType sensorType, Long value, Timestamp timestamp) {
}
