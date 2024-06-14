package com.loschiferos.ztech.loan.interfaces.rest.transform;


import com.loschiferos.ztech.loan.domain.model.commands.CreateFlowerpotAssignmentCommand;
import com.loschiferos.ztech.loan.interfaces.rest.resources.CreateFlowerpotAssignmentResource;

public class CreateFlowerpotAssignmentCommandFromResourceAssembler {
    public static CreateFlowerpotAssignmentCommand toCommandFromResource(CreateFlowerpotAssignmentResource resource) {
        return new CreateFlowerpotAssignmentCommand(resource.plantOwnerId(), resource.flowerpotId(), resource.plantTypeId(), resource.name().getName(), resource.photo(), resource.startDate(), resource.endDate());
    }
}
