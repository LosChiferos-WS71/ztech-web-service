package com.loschiferos.ztech.profile.interfaces.rest.resources;

import java.util.Date;

public record CreatePlantOwnerResource(
        String name,
        String email,
        String password,
        String address,
        Long phone,
        String photo,
        Long dni,
        Date birthday,
        String gender
) {
}
