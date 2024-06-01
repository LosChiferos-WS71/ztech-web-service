package com.loschiferos.ztech.flowerpot.domain.model.aggregates;

import com.loschiferos.ztech.flowerpot.domain.model.valueobjects.SensorsData;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Flowerpot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;

    private boolean active;

    @Embedded
    private SensorsData sensorsData;

    public Flowerpot(String code, boolean active, SensorsData sensorsData) {
        this();
        this.code = code;
        this.active = active;
        this.sensorsData = sensorsData;
    }
}
