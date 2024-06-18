package com.loschiferos.ztech.pot.domain.model.aggregates;

import com.loschiferos.ztech.pot.domain.model.entities.Sensor;
import com.loschiferos.ztech.pot.domain.model.valueobjects.SensorList;
import com.loschiferos.ztech.pot.domain.model.valueobjects.SensorType;
import com.loschiferos.ztech.pot.domain.model.valueobjects.SensorsData;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.logging.log4j.util.Strings;

import java.util.List;

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
        this.active = true;
        this.sensorsData = new SensorsData();
        this.sensorList = new SensorList();
    }

    public Flowerpot(String code, boolean active, SensorsData sensorsData) {
        this();
        this.code = code;
        this.active = active;
        this.sensorsData = sensorsData;
    }

    public void createSensor(SensorType type, Long value) {
        Sensor sensor = new Sensor(type, value, this);
        this.sensorList.createSensor(sensor);

        if(type == SensorType.TEMPERATURE) {
            this.sensorsData.setLastTemperature(value);
        } else if(type == SensorType.HUMIDITY) {
            this.sensorsData.setLastHumidity(value);
        } else if(type == SensorType.SUNLIGHT) {
            this.sensorsData.setLastSunlight(value);
        }
    }

    public List<Sensor> getAllSensors() {
        return sensorList.getAllSensors();
    }
}
