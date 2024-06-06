package com.loschiferos.ztech.loan.interfaces.rest.transform;


import com.loschiferos.ztech.loan.domain.model.commands.CreateFlowerpotAssigmentCommand;
import com.loschiferos.ztech.loan.interfaces.rest.resources.CreateFlowerpotAssigmentResource;

public class CreateFlowerpotAssigmentCommandFromResourceAssembler {
    public static CreateFlowerpotAssigmentCommand toCommandFromResource(CreateFlowerpotAssigmentResource resource) {
        return new CreateFlowerpotAssigmentCommand(resource.plantOwnerId(), resource.flowerpotId(), resource.platTypeId(), resource.name().getName(), resource.photo(), resource.startDate(), resource.endDate());
    }
}
