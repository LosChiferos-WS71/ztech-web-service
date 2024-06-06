package com.loschiferos.ztech.profile.domain.model.valueobjects;

public record Profile(
    String name,
    String email,
    String password,
    String address,
    Long phone,
    String photo
) {
    public Profile() {
        this(null, null, null, null, null, null);
    }

    public Profile {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be null or blank");
        }
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email cannot be null or blank");
        }
        if (password == null || password.isBlank()) {
            throw new IllegalArgumentException("Password cannot be null or blank");
        }
        if (address == null || address.isBlank()) {
            throw new IllegalArgumentException("Address cannot be null or blank");
        }
        if (phone == null) {
            throw new IllegalArgumentException("Phone cannot be null or blank");
        }
        if (photo == null || photo.isBlank()) {
            throw new IllegalArgumentException("Photo cannot be null or blank");
        }
    }
}
