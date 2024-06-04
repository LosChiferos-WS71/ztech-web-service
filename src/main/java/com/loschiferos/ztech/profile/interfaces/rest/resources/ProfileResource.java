package com.loschiferos.ztech.profile.interfaces.rest.resources;

import lombok.Getter;

public record ProfileResource(
        Long id,
        String fullName,
        String email,
        String password,
        String streetAddress,
        int phone,
        String photo
) {
    @Override
    public Long id() {
        return id;
    }

    @Override
    public String fullName() {
        return fullName;
    }

    @Override
    public String email() {
        return email;
    }

    @Override
    public String password() {
        return password;
    }

    @Override
    public String streetAddress() {
        return streetAddress;
    }

    @Override
    public int phone() {
        return phone;
    }

    @Override
    public String photo() {
        return photo;
    }
}
