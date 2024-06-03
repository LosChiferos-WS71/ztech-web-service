package com.loschiferos.ztech.profile.domain.model.valueobjects;

public record PersonName(String firstName, String lastName) {
    public PersonName() {
        this(null, null);
    }

    public PersonName {
        if (firstName == null || firstName.isBlank()) {
            throw new IllegalArgumentException("First name cannot be null or blank");
        }
        if(lastName == null || lastName.isBlank()) {
            throw new IllegalArgumentException("Last name cannot be null or blank");
        }
    }

    public String getFullName() {
        return String.format("%s %s", firstName, lastName);
    }
}
