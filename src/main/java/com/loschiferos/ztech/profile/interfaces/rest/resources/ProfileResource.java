package com.loschiferos.ztech.profile.interfaces.rest.resources;

public record ProfileResource(
        Long id,
        String fullName,
        String email,
        String password,
        String streetAddress,
        int phone,
        String photo
) {}
