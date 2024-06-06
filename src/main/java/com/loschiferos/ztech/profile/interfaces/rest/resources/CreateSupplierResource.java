package com.loschiferos.ztech.profile.interfaces.rest.resources;

public record CreateSupplierResource(
        String name,
        String email,
        String password,
        String address,
        Long phone,
        String photo,
        String ruc
) {
}
