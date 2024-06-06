package com.loschiferos.ztech.loan.interfaces.rest.resources;

import com.loschiferos.ztech.loan.domain.model.valueobjects.Name;

public record CreateFlowerpotAssigmentResource(Long plantOwnerId, Long flowerpotId, Long platTypeId, Name name, String photo, String startDate, String endDate) {
}
