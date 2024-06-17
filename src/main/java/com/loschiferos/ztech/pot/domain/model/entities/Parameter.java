package com.loschiferos.ztech.pot.domain.model.entities;

import com.loschiferos.ztech.pot.domain.model.aggregates.PlantType;
import com.loschiferos.ztech.pot.domain.model.valueobjects.ParameterType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Parameter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "plant_type_id")
    private PlantType plantType;

    @Getter
    private ParameterType parameterType;

    @Getter
    private Long value;

    public Parameter(PlantType plantType, ParameterType parameterType, Long value) {
        this.plantType = plantType;
        this.parameterType = parameterType;
        this.value = value;
    }
}
