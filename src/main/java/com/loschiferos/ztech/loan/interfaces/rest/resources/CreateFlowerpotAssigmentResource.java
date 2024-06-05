package com.loschiferos.ztech.loan.interfaces.rest.resources;

import com.loschiferos.ztech.loan.domain.model.valueobjects.Name;

public record CreateFlowerpotAssigmentResource(Name name, String photo, String startDate, String endDate, Long flowerpotMetricId) {
}
