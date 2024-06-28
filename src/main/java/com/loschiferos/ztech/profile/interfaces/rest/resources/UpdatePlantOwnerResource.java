package com.loschiferos.ztech.profile.interfaces.rest.resources;

import java.util.Date;

public record UpdatePlantOwnerResource(
        String name,
        String email,
        String address,
        Long phone,
        String photo,
        Long dni,
        Date birthday,
        String gender) {
}
