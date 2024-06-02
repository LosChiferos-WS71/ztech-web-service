package com.loschiferos.ztech.pot.domain.model.aggregates;

import com.loschiferos.ztech.pot.domain.model.valueobjects.SensorsData;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
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
        this.code = code;
        this.active = active;
        this.sensorsData = sensorsData;
    }
}
