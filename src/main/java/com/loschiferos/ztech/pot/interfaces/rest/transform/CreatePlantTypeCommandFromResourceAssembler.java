package com.loschiferos.ztech.pot.interfaces.rest.transform;

import com.loschiferos.ztech.pot.domain.model.commands.CreatePlantTypeCommand;
import com.loschiferos.ztech.pot.interfaces.rest.resources.CreatePlantTypeResource;

public class CreatePlantTypeCommandFromResourceAssembler {
    public static CreatePlantTypeCommand toCommandFromResource(CreatePlantTypeResource resource) {
        return new CreatePlantTypeCommand(resource.name(), resource.scientificName(), resource.photo(), resource.description());
    }
}
