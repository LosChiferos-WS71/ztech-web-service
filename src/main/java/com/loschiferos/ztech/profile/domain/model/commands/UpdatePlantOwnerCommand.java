package com.loschiferos.ztech.profile.domain.model.commands;

import java.util.Date;

public record UpdatePlantOwnerCommand(Long id, String name, String email, String address, Long phone, String photo, Long dni, Date birthday, String gender) {
}
