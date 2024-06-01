package com.loschiferos.ztech.flowerpot.domain.model.valueobjects;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
public class SensorsData {
    private int lastTemperature;
    private int lastHumidity;
    private int lastSunlight;

    public SensorsData(int lastTemperature, int lastHumidity, int lastSunlight) {
        this.lastTemperature = lastTemperature;
        this.lastHumidity = lastHumidity;
        this.lastSunlight = lastSunlight;
    }
}
