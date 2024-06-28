package com.loschiferos.ztech.inventory.interfaces.rest.transform;

import com.loschiferos.ztech.inventory.domain.model.commands.CreateSupplyCommand;
import com.loschiferos.ztech.inventory.interfaces.rest.resources.CreateSupplyResource;

public class CreateSupplyCommandFromResourceAssembler {

    public static CreateSupplyCommand toCommandFromResource(CreateSupplyResource resource) {
        return new CreateSupplyCommand(resource.name(), resource.quantity());
    }
}