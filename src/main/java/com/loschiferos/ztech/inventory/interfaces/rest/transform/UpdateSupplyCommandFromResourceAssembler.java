package com.loschiferos.ztech.inventory.interfaces.rest.transform;

import com.loschiferos.ztech.inventory.domain.model.commands.UpdateSupplyCommand;
import com.loschiferos.ztech.inventory.interfaces.rest.resources.CreateSupplyResource;

public class UpdateSupplyCommandFromResourceAssembler {

    public static UpdateSupplyCommand toCommandFromResource(Long supplyId, CreateSupplyResource resource) {
        return new UpdateSupplyCommand(supplyId, resource.name(), resource.quantity());
    }
}