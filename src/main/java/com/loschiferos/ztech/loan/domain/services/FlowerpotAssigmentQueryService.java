package com.loschiferos.ztech.loan.domain.services;

import com.loschiferos.ztech.loan.domain.model.aggregates.FlowerpotAssigment;
import com.loschiferos.ztech.loan.domain.model.queries.GetAllFlowerpotAssigmentsQuery;
import com.loschiferos.ztech.loan.domain.model.queries.GetAssigmentMetricsQuery;
import com.loschiferos.ztech.loan.domain.model.queries.GetFlowerpotAssigmentsByIdQuery;

import java.util.List;
import java.util.Optional;

public interface FlowerpotAssigmentQueryService {
    Optional<FlowerpotAssigment> handle(GetFlowerpotAssigmentsByIdQuery query);
    List<FlowerpotAssigment> handle(GetAllFlowerpotAssigmentsQuery query);

    //List<FlowerpotAssigment> handle(GetAssigmentMetricsQuery query);
}
