package com.loschiferos.ztech.pot.interfaces.rest.transform;

import com.loschiferos.ztech.pot.domain.model.aggregates.PlantType;
import com.loschiferos.ztech.pot.interfaces.rest.resources.PlantTypeResource;

public class PlantTypeResourceFromEntityAssembler {
    public static PlantTypeResource toResourceFromEntity(PlantType entity) {
        return new PlantTypeResource(entity.getId(), entity.getName(), entity.getScientificName(), entity.getPhoto(), entity.getDescription());
    }
}
