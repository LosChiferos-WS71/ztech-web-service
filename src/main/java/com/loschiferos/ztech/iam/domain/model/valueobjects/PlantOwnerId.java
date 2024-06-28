package com.loschiferos.ztech.iam.domain.model.valueobjects;

public record PlantOwnerId(Long plantOwnerId) {
    public PlantOwnerId() { this(0L); }

    public PlantOwnerId {
        if (plantOwnerId < 0) {
            throw new IllegalArgumentException("Plant owner id cannot be negative");
        }
    }
}
