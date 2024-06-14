package com.loschiferos.ztech.loan.interfaces.rest.resources;

import com.loschiferos.ztech.loan.domain.model.valueobjects.Name;

public record CreateFlowerpotAssignmentResource(Long plantOwnerId, Long flowerpotId, Long plantTypeId, Name name, String photo, String startDate, String endDate) {
}
