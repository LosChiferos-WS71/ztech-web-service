package com.loschiferos.ztech.pot.interfaces.rest.resources;

public record FlowerpotResource(Long id, String code, boolean active, int lastTemperature, int lastHumidity, int lastSunlight) {
}
