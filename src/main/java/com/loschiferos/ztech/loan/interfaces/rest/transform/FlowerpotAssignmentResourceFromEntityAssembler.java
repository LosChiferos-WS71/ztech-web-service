package com.loschiferos.ztech.loan.interfaces.rest.transform;


import com.loschiferos.ztech.loan.domain.model.aggregates.FlowerpotAssignment;
import com.loschiferos.ztech.loan.interfaces.rest.resources.FlowerpotAssignmentResource;

public class FlowerpotAssignmentResourceFromEntityAssembler {
    public static FlowerpotAssignmentResource toResourceFromEntity(FlowerpotAssignment entity) {
        return new FlowerpotAssignmentResource(entity.getPlantTypeId().plantTypeId(), entity.getName(), entity.getPhoto(), entity.getDateRange());
    }
}
