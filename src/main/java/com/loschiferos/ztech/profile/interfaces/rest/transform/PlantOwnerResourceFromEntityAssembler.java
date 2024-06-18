package com.loschiferos.ztech.profile.interfaces.rest.transform;

import com.loschiferos.ztech.profile.domain.model.aggregates.PlantOwner;
import com.loschiferos.ztech.profile.interfaces.rest.resources.PlantOwnerResource;

public class PlantOwnerResourceFromEntityAssembler {
    public static PlantOwnerResource toResourceFromEntity(PlantOwner entity) {
        return new PlantOwnerResource(entity.getId(), entity.getProfile().name(), entity.getProfile().email(), entity.getProfile().address(), entity.getProfile().phone(), entity.getProfile().photo(), entity.getDni(), entity.getBirthday(), entity.getGender());
    }
}
