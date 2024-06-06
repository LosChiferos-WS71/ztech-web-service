package com.loschiferos.ztech.loan.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record PlantTypeId(Long plantTypeId) {
    public PlantTypeId() { this(0L); }

    public PlantTypeId {
        if (plantTypeId < 0) {
            throw new IllegalArgumentException("Plant type id cannot be negative");
        }
    }
}
