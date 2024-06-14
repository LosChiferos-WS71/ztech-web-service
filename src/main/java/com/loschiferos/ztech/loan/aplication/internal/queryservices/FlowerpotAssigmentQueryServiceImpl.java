package com.loschiferos.ztech.loan.aplication.internal.queryservices;

import com.loschiferos.ztech.loan.domain.model.aggregates.FlowerpotAssigment;
import com.loschiferos.ztech.loan.domain.model.queries.GetAllFlowerpotAssigmentsQuery;
import com.loschiferos.ztech.loan.domain.model.queries.GetAssigmentMetricsQuery;
import com.loschiferos.ztech.loan.domain.model.queries.GetFlowerpotAssigmentsByIdQuery;
import com.loschiferos.ztech.loan.domain.model.queries.GetFlowerpotIdsByPlantOwnerIdQuery;
import com.loschiferos.ztech.loan.domain.services.FlowerpotAssigmentQueryService;
import com.loschiferos.ztech.loan.infrastructure.persistence.jpa.repositories.FlowerpotAssigmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FlowerpotAssigmentQueryServiceImpl implements FlowerpotAssigmentQueryService {
    private final FlowerpotAssigmentRepository flowerpotAssigmentRepository;

    public FlowerpotAssigmentQueryServiceImpl(FlowerpotAssigmentRepository flowerpotAssigmentRepository) {
        this.flowerpotAssigmentRepository = flowerpotAssigmentRepository;
    }

    @Override
    public Optional<FlowerpotAssigment> handle(GetFlowerpotAssigmentsByIdQuery query) {
        return flowerpotAssigmentRepository.findById(query.flowerpotAssigmentId());
    }

    @Override
    public List<FlowerpotAssigment> handle(GetAllFlowerpotAssigmentsQuery query) {
        return flowerpotAssigmentRepository.findAll();
    }

    @Override
    public List<Long> handle(GetFlowerpotIdsByPlantOwnerIdQuery query) {
        return flowerpotAssigmentRepository.findFlowerpotIdsByPlantOwnerId(query.plantOwnerId());
    }
}
