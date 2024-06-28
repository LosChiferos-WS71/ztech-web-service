package com.loschiferos.ztech.pot.domain.model.valueobjects;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
public class SensorsData {
    private Long lastTemperature;
    private Long lastHumidity;
    private Long lastSunlight;

    public SensorsData(Long lastTemperature, Long lastHumidity, Long lastSunlight) {
        this.lastTemperature = lastTemperature;
        this.lastHumidity = lastHumidity;
        this.lastSunlight = lastSunlight;
    }
}
