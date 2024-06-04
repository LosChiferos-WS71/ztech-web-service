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

    private int internalSerialNumber;

    private SensorType type;

    private int value;

    private Timestamp timestamp;

    public Sensor(int internalSerialNumber, SensorType type, int value, Flowerpot flowerpot) {
        this.internalSerialNumber = internalSerialNumber;
        this.type = type;
        this.value = value;
        this.timestamp = new Timestamp(System.currentTimeMillis());
        this.flowerpot = flowerpot;
    }
}
