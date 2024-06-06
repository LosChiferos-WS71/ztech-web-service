package com.loschiferos.ztech.loan.interfaces.rest.transform;


import com.loschiferos.ztech.loan.domain.model.aggregates.FlowerpotAssigment;
import com.loschiferos.ztech.loan.interfaces.rest.resources.FlowerpotAssigmentResource;

public class FlowerpotAssigmentResourceFromEntityAssembler {
    public static FlowerpotAssigmentResource toResourceFromEntity(FlowerpotAssigment entity) {
        return new FlowerpotAssigmentResource(entity.getName(), entity.getPhoto(), entity.getDateRange());
    }
}
