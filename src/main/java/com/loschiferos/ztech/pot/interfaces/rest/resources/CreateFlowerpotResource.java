package com.loschiferos.ztech.pot.interfaces.rest.resources;

public record CreateFlowerpotResource(String code, boolean active, int lastTemperature, int lastHumidity, int lastSunlight) {
    public CreateFlowerpotResource {
        if (code == null || code.isBlank())
            throw new IllegalArgumentException("code cannot be null or empty");
    }
}
