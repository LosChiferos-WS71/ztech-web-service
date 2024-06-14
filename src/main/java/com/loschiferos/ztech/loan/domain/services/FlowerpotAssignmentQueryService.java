package com.loschiferos.ztech.loan.domain.services;

import com.loschiferos.ztech.loan.domain.model.aggregates.FlowerpotAssignment;
import com.loschiferos.ztech.loan.domain.model.queries.GetAllFlowerpotAssignmentsQuery;
import com.loschiferos.ztech.loan.domain.model.queries.GetFlowerpotAssignmentsByIdQuery;
import com.loschiferos.ztech.loan.domain.model.queries.GetFlowerpotIdsByPlantOwnerIdQuery;

import java.util.List;
import java.util.Optional;

public interface FlowerpotAssignmentQueryService {
    Optional<FlowerpotAssignment> handle(GetFlowerpotAssignmentsByIdQuery query);
    List<FlowerpotAssignment> handle(GetAllFlowerpotAssignmentsQuery query);
    List<Long> handle(GetFlowerpotIdsByPlantOwnerIdQuery query);
}
