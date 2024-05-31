package com.loschiferos.ztech.flowerpot.domain.model.aggregates;

import com.loschiferos.ztech.flowerpot.domain.model.entities.Plant;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class PlantRecommendation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @ManyToOne
    @JoinColumn(name = "plant_id")
    private Plant plantId;

    private String text;

    public PlantRecommendation(Plant plantId, String text) {
        this();
        this.plantId = plantId;
        this.text = text;
    }
}
