package com.loschiferos.ztech.pot.domain.services;

import com.loschiferos.ztech.pot.domain.model.commands.CreateParameterCommand;
import com.loschiferos.ztech.pot.domain.model.commands.CreatePlantTypeCommand;

public interface PlantTypeCommandService {
    Long handle(CreatePlantTypeCommand command);
    void handle(CreateParameterCommand command);
}
