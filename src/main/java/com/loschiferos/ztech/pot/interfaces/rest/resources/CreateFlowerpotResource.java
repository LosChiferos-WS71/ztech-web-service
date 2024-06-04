package com.loschiferos.ztech.pot.interfaces.rest.resources;

public record CreateFlowerpotResource(String code, boolean active, int lastTemperature, int lastHumidity, int lastSunlight) {
}
