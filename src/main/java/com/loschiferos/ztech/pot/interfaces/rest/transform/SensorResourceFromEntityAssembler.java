package com.loschiferos.ztech.pot.interfaces.rest.transform;

import com.loschiferos.ztech.pot.domain.model.entities.Sensor;
import com.loschiferos.ztech.pot.interfaces.rest.resources.SensorResource;

public class SensorResourceFromEntityAssembler {
    public static SensorResource toResourceFromEntity(Sensor entity) {
        return new SensorResource(entity.getType(), entity.getValue(), entity.getTimestamp());
    }
}
