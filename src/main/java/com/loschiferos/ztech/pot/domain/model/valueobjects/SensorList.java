package com.loschiferos.ztech.pot.domain.model.valueobjects;

import com.loschiferos.ztech.pot.domain.model.entities.Sensor;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Embeddable;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

@Embeddable
public class SensorList {
    @OneToMany(mappedBy = "flowerpot", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Sensor> sensors;

    public SensorList() {
        this.sensors = new ArrayList<>();
    }

    public void createSensor(Sensor sensor) {
        sensors.add(sensor);
    }
}