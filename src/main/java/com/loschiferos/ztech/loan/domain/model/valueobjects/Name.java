package com.loschiferos.ztech.loan.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public class Name {

    private String name;

    public Name() {
        this.name = null;
    }

    public Name(String value) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        this.name = value;
    }
    public String getName() {
        return name;
    }


}
