package com.loschiferos.ztech.inventory.interfaces.rest.transform;

import com.loschiferos.ztech.inventory.domain.model.aggregates.Supply;
import com.loschiferos.ztech.inventory.interfaces.rest.resources.SupplyResource;

public class SupplyResourceFromEntityAssembler {

    public static SupplyResource toResourceFromEntity(Supply supply) {
        if (supply == null) {
            return null;
        }
        return new SupplyResource(supply.getId(), supply.getName(), supply.getQuantity());
    }
}