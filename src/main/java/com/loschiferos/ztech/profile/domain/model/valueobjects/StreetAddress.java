package com.loschiferos.ztech.profile.domain.model.valueobjects;

public record StreetAddress(String street, String number, String city, String zipCode) {
    public StreetAddress() {
        this(null, null, null, null);
    }

    public StreetAddress(String street, String city, String zipCode) {
        this(street, null, city, zipCode);
    }

    public String getStreetAddress() {
        return String.format("%s %sth, %s, %s", street, number, city, zipCode);
    }

    public StreetAddress {
        if (street == null || street.isBlank()) {
            throw new IllegalArgumentException("Street cannot be null or blank");
        }
        if (city == null || city.isBlank()) {
            throw new IllegalArgumentException("City cannot be null or blank");
        }

        if (zipCode == null || zipCode.isBlank()) {
            throw new IllegalArgumentException("Zip code cannot be null or blank");
        }

    }
}
