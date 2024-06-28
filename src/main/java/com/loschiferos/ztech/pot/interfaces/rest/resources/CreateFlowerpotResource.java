package com.loschiferos.ztech.pot.interfaces.rest.resources;

public record CreateFlowerpotResource(String code, boolean active, Long lastTemperature, Long lastHumidity, Long lastSunlight) {
}
