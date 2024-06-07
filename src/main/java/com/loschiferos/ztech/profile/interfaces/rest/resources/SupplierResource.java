package com.loschiferos.ztech.profile.interfaces.rest.resources;

public record SupplierResource(
        Long id,
        String name,
        String email,
        String address,
        Long phone,
        String photo,
        String ruc
) {
}
