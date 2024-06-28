package com.loschiferos.ztech.pot.domain.model.commands;

public record CreateFlowerpotCommand(String code, boolean active, Long lastTemperature, Long lastHumidity, Long lastSunlight) {
}
