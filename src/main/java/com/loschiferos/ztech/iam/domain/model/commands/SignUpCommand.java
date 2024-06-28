package com.loschiferos.ztech.iam.domain.model.commands;

import com.loschiferos.ztech.iam.domain.model.entities.Role;

import java.util.Date;
import java.util.List;

public record SignUpCommand(
        String name,
        String email,
        String address,
        Long phone,
        String photo,
        Long dni,
        Date birthday,
        String gender,
        String ruc,
        List<Role> roles)
{ }
