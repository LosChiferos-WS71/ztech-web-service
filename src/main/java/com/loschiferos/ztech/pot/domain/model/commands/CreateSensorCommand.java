package com.loschiferos.ztech.pot.domain.model.commands;

public record CreateSensorCommand(Long flowerpotId, Double internalSerialNumber, Long type, Double value) {
}
