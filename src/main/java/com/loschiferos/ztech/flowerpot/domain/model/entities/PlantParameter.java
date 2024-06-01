package com.loschiferos.ztech.flowerpot.domain.model.entities;

import com.loschiferos.ztech.flowerpot.domain.model.aggregates.PlantType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class PlantParameter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private PlantType plantType;

    @ManyToOne
    private Parameter parameter;

    private int value;
}
