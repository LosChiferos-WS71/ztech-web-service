package com.loschiferos.ztech.pot.interfaces.rest.transform;

import com.loschiferos.ztech.pot.domain.model.commands.CreateParameterCommand;
import com.loschiferos.ztech.pot.interfaces.rest.resources.CreateParameterResource;

public class CreateParameterCommandFromResourceAssembler {
    public static CreateParameterCommand toCommandFromResource(CreateParameterResource resource) {
        return new CreateParameterCommand(resource.plantTypeId(), resource.type(), resource.value());
    }
}
