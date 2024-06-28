package com.loschiferos.ztech.inventory.interfaces.rest.transform;

import com.loschiferos.ztech.inventory.domain.model.commands.CreateOrderCommand;
import com.loschiferos.ztech.inventory.interfaces.rest.resources.CreateOrderResource;

public class CreateOrderCommandFromResourceAssembler {
    public static CreateOrderCommand toCommandFromResource(CreateOrderResource resource) {
        return new CreateOrderCommand(resource.suppliersId(), resource.subject(), resource.description(), resource.state());
    }
}