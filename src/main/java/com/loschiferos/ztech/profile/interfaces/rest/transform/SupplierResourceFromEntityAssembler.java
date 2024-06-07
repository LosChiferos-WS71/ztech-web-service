package com.loschiferos.ztech.profile.interfaces.rest.transform;

import com.loschiferos.ztech.profile.domain.model.aggregates.Supplier;
import com.loschiferos.ztech.profile.interfaces.rest.resources.SupplierResource;

public class SupplierResourceFromEntityAssembler {
    public static SupplierResource toResourceFromEntity(Supplier entity) {
        return new SupplierResource(entity.getId(), entity.getProfile().name(), entity.getProfile().email(), entity.getProfile().address(), entity.getProfile().phone(), entity.getProfile().photo(), entity.getRuc());
    }
}
