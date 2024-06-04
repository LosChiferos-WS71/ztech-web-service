package com.loschiferos.ztech.profile.interfaces.rest.resources;

import java.util.Date;

public record PlantOwnerResource(
        Long id,
        String name,
        String email,
        String password,
        String address,
        int phone,
        String photo,
        int dni,
        Date birthday,
        String gender
) {
}
