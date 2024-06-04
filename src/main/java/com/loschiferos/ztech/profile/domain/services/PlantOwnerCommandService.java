package com.loschiferos.ztech.profile.domain.services;

import com.loschiferos.ztech.profile.domain.model.commands.CreatePlantOwnerCommand;

public interface PlantOwnerCommandService {
    Long handle(CreatePlantOwnerCommand command);
}
