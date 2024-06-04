package com.loschiferos.ztech.profile.domain.model.commands;

public record CreateSupplierCommand(
        String name,
        String email,
        String password,
        String address,
        int phone,
        String photo,
        String ruc
) {
}
