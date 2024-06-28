package com.loschiferos.ztech.profile.interfaces.rest.transform;

import com.loschiferos.ztech.profile.domain.model.commands.UpdatePlantOwnerCommand;
import com.loschiferos.ztech.profile.interfaces.rest.resources.UpdatePlantOwnerResource;

public class UpdatePlantOwnerCommandFromResourceAssembler {
    public static UpdatePlantOwnerCommand toCommandFromResource(Long plantOwnerId, UpdatePlantOwnerResource resource) {
        return new UpdatePlantOwnerCommand(
                plantOwnerId,
                resource.name(),
                resource.email(),
                resource.address(),
                resource.phone(),
                resource.photo(),
                resource.dni(),
                resource.birthday(),
                resource.gender());
    }
}
