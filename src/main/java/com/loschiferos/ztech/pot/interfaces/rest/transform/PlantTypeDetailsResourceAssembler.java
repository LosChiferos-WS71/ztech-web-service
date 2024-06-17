package com.loschiferos.ztech.pot.interfaces.rest.transform;

import com.loschiferos.ztech.pot.interfaces.rest.resources.ParameterResource;
import com.loschiferos.ztech.pot.interfaces.rest.resources.PlantTypeDetailsResource;
import com.loschiferos.ztech.pot.interfaces.rest.resources.PlantTypeResource;

import java.util.List;

public class PlantTypeDetailsResourceAssembler {
    public static PlantTypeDetailsResource toResource(PlantTypeResource plantType, List<ParameterResource> parameters) {
        return new PlantTypeDetailsResource(plantType, parameters);
    }
}
