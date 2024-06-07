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
}
