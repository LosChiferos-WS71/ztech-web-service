package com.loschiferos.ztech.profile.interfaces.rest.resources;

public record CreateProfileResource(
        String firstName,
        String lastName,
        String email,
        String password,
        String street,
        String number,
        String city,
        String zipCode,
        int phone,
        String photo
) {
}
