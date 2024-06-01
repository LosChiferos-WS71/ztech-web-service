package com.loschiferos.ztech.pot.domain.model.entities;

import com.loschiferos.ztech.pot.domain.model.aggregates.Flowerpot;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class FlowerpotSensor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "flowerpot_id")
    private Flowerpot flowerpot;

    @ManyToOne
    @JoinColumn(name = "sensor_id")
    private Sensor sensor;

    private int value;

    private Timestamp timestamp;

    public FlowerpotSensor(Flowerpot flowerpot, Sensor sensor, int value, Timestamp timestamp) {
        this();
        this.flowerpot = flowerpot;
        this.sensor = sensor;
        this.value = value;
        this.timestamp = timestamp;
    }
}
