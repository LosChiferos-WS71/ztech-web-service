package com.loschiferos.ztech.flowerpot.domain.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Plant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private int maxTemperature;

    private int maxSunlight;

    private int maxHumidity;

    private int minTemperature;

    private int minSunlight;

    private int minHumidity;

    public Plant(String name, String description, int maxTemperature, int maxSunlight, int maxHumidity, int minTemperature, int minSunlight, int minHumidity) {
        this();
        this.name = name;
        this.description = description;
        this.maxTemperature = maxTemperature;
        this.maxSunlight = maxSunlight;
        this.maxHumidity = maxHumidity;
        this.minTemperature = minTemperature;
        this.minSunlight = minSunlight;
        this.minHumidity = minHumidity;
    }

    public Plant() {
        this.name = "";
        this.description = "";
        this.maxTemperature = 0;
        this.maxSunlight = 0;
        this.maxHumidity = 0;
        this.minTemperature = 0;
        this.minSunlight = 0;
        this.minHumidity = 0;
    }
}
