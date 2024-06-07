package com.loschiferos.ztech.profile.interfaces.rest.transform;

import com.loschiferos.ztech.profile.domain.model.commands.CreatePlantOwnerCommand;
import com.loschiferos.ztech.profile.interfaces.rest.resources.CreatePlantOwnerResource;

public class CreatePlantOwnerCommandFromResourceAssembler {
    public static CreatePlantOwnerCommand toCommandFromResource(CreatePlantOwnerResource resource) {
        return new CreatePlantOwnerCommand(resource.name(), resource.email(), resource.address(), resource.phone(), resource.photo(), resource.dni(), resource.birthday(), resource.gender());
    }
}
