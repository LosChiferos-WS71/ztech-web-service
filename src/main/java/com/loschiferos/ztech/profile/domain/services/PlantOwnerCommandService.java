package com.loschiferos.ztech.profile.domain.services;

import com.loschiferos.ztech.profile.domain.model.aggregates.PlantOwner;
import com.loschiferos.ztech.profile.domain.model.commands.CreatePlantOwnerCommand;
import com.loschiferos.ztech.profile.domain.model.commands.UpdatePlantOwnerCommand;

import java.util.Optional;

public interface PlantOwnerCommandService {
    Long handle(CreatePlantOwnerCommand command);
    Optional<PlantOwner> handle(UpdatePlantOwnerCommand command);
}
