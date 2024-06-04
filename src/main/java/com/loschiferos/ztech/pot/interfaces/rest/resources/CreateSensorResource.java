package com.loschiferos.ztech.pot.interfaces.rest.resources;

public record CreateSensorResource(Long flowerpotId, Double internalSerialNumber, Long type, Double value) {
}
