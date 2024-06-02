package com.loschiferos.ztech.pot.interfaces.rest.transform;

import com.loschiferos.ztech.pot.domain.model.commands.CreateFlowerpotCommand;
import com.loschiferos.ztech.pot.interfaces.rest.resources.CreateFlowerpotResource;

public class CreateFlowerpotCommandFromResourceAssembler {
    public static CreateFlowerpotCommand toCommandFromResource(CreateFlowerpotResource resource) {
        return new CreateFlowerpotCommand(resource.code(), resource.active(), resource.lastTemperature(), resource.lastHumidity(), resource.lastSunlight());
    }
}
