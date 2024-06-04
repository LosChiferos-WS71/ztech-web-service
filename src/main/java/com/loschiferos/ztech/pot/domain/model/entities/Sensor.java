package com.loschiferos.ztech.pot.domain.model.entities;

import com.loschiferos.ztech.pot.domain.model.aggregates.Flowerpot;
import com.loschiferos.ztech.pot.domain.model.valueobjects.SensorType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Sensor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "flowerpot_id")
    private Flowerpot flowerpot;

    private Double internalSerialNumber;

    private SensorType type;

    private Double value;

    private Timestamp timestamp;

    public Sensor(Double internalSerialNumber, SensorType type, Double value, Flowerpot flowerpot) {
        this.internalSerialNumber = internalSerialNumber;
        this.type = type;
        this.value = value;
        this.timestamp = new Timestamp(System.currentTimeMillis());
        this.flowerpot = flowerpot;
    }
}
