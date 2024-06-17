package com.loschiferos.ztech.pot.interfaces.rest.transform;

import com.loschiferos.ztech.pot.domain.model.entities.Parameter;
import com.loschiferos.ztech.pot.interfaces.rest.resources.ParameterResource;

public class ParameterResourceFromEntityAssembler {
    public static ParameterResource toResourceFromEntity(Parameter entity) {
        return new ParameterResource(entity.getParameterType(), entity.getValue());
    }
}
