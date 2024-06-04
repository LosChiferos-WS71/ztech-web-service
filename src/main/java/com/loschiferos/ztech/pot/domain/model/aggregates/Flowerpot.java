package com.loschiferos.ztech.pot.domain.model.aggregates;

import com.loschiferos.ztech.pot.domain.model.entities.Sensor;
import com.loschiferos.ztech.pot.domain.model.valueobjects.SensorList;
import com.loschiferos.ztech.pot.domain.model.valueobjects.SensorType;
import com.loschiferos.ztech.pot.domain.model.valueobjects.SensorsData;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.logging.log4j.util.Strings;

@Entity
@Getter
@AllArgsConstructor
public class Flowerpot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;

    private boolean active;

    @Embedded
    private SensorsData sensorsData;

    @Embedded
    private final SensorList sensorList;

    public Flowerpot() {
        this.code = Strings.EMPTY;
        this.active = false;
        this.sensorsData = new SensorsData();
        this.sensorList = new SensorList();
    }

    public Flowerpot(String code, boolean active, SensorsData sensorsData) {
        this();
        this.code = code;
        this.active = active;
        this.sensorsData = sensorsData;
    }

    public void createSensor(Double internalSerialNumber, SensorType type, Double value) {
        Sensor sensor = new Sensor(internalSerialNumber, type, value, this);
        this.sensorList.createSensor(sensor);
    }
}
