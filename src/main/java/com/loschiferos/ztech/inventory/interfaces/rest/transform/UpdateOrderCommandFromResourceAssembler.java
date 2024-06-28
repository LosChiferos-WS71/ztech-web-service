package com.loschiferos.ztech.inventory.interfaces.rest.transform;

import com.loschiferos.ztech.inventory.domain.model.commands.UpdateOrderCommand;
import com.loschiferos.ztech.inventory.interfaces.rest.resources.CreateOrderResource;

public class UpdateOrderCommandFromResourceAssembler {
    public static UpdateOrderCommand toCommandFromResource(Long id, CreateOrderResource resource) {
        return new UpdateOrderCommand(
                id,
                resource.suppliersId(),
                resource.subject(),
                resource.description(),
                resource.state()
        );
    }
}