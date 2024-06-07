package com.loschiferos.ztech.profile.domain.model.commands;

import java.util.Date;

public record CreatePlantOwnerCommand(
        String name,
        String email,
        String address,
        Long phone,
        String photo,
        Long dni,
        Date birthday,
        String gender
) {
}
