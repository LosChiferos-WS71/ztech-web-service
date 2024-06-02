package com.loschiferos.ztech.pot.domain.model.commands;

public record CreateFlowerpotCommand(String code, boolean active, int lastTemperature, int lastHumidity, int lastSunlight) {
}
