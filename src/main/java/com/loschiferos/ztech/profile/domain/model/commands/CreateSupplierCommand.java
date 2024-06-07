package com.loschiferos.ztech.profile.domain.model.commands;

public record CreateSupplierCommand(
        String name,
        String email,
        String address,
        Long phone,
        String photo,
        String ruc
) {
}
