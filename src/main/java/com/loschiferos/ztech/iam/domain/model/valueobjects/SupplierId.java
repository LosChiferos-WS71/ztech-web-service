package com.loschiferos.ztech.iam.domain.model.valueobjects;

public record SupplierId(Long supplierId) {
    public SupplierId() { this(0L); }

    public SupplierId {
        if (supplierId < 0) {
            throw new IllegalArgumentException("Plant owner id cannot be negative");
        }
    }
}
