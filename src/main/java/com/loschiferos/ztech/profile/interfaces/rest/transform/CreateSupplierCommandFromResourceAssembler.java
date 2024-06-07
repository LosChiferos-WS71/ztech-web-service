package com.loschiferos.ztech.profile.interfaces.rest.transform;

import com.loschiferos.ztech.profile.domain.model.commands.CreateSupplierCommand;
import com.loschiferos.ztech.profile.interfaces.rest.resources.CreateSupplierResource;

public class CreateSupplierCommandFromResourceAssembler {
    public static CreateSupplierCommand toCommandFromResource(CreateSupplierResource resource) {
        return new CreateSupplierCommand(resource.name(), resource.email(), resource.address(), resource.phone(), resource.photo(), resource.ruc());
    }
}
