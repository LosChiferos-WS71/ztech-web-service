package com.loschiferos.ztech.pot.interfaces.rest.transform;

import com.loschiferos.ztech.pot.domain.model.aggregates.Flowerpot;
import com.loschiferos.ztech.pot.interfaces.rest.resources.FlowerpotResource;

public class FlowerpotResourceFromEntityAssembler {
    public static FlowerpotResource toResourceFromEntity(Flowerpot entity) {
        return new FlowerpotResource(entity.getId(), entity.getCode(), entity.isActive(),
                entity.getSensorsData().getLastTemperature(),
                entity.getSensorsData().getLastHumidity(),
                entity.getSensorsData().getLastSunlight());
    }
}
