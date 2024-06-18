package com.loschiferos.ztech.pot.interfaces.rest.transform;

import com.loschiferos.ztech.pot.domain.model.commands.CreateSensorCommand;
import com.loschiferos.ztech.pot.interfaces.rest.resources.CreateSensorResource;

public class CreateSensorCommandFromResourceAssembler {
    public static CreateSensorCommand toCommandFromResource(CreateSensorResource resource) {
        return new CreateSensorCommand(resource.flowerpotId(), resource.type(), resource.value());
    }
}
