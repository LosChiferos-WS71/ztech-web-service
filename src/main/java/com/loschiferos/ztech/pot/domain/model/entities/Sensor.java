package com.loschiferos.ztech.pot.domain.model.entities;

import com.loschiferos.ztech.pot.domain.model.aggregates.Flowerpot;
import com.loschiferos.ztech.pot.domain.model.valueobjects.SensorType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
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

    @Getter
    private SensorType type;

    @Getter
    private Long value;

    @Getter
    private Timestamp timestamp;

    public Sensor(SensorType type, Long value, Flowerpot flowerpot) {
        this.type = type;
        this.value = value;
        this.timestamp = new Timestamp(System.currentTimeMillis());
        this.flowerpot = flowerpot;
    }
}
