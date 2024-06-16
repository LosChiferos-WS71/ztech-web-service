package com.loschiferos.ztech.loan.aplication.internal.queryservices;

import com.loschiferos.ztech.loan.domain.model.aggregates.FlowerpotAssignment;
import com.loschiferos.ztech.loan.domain.model.queries.*;
import com.loschiferos.ztech.loan.domain.services.FlowerpotAssignmentQueryService;
import com.loschiferos.ztech.loan.infrastructure.persistence.jpa.repositories.FlowerpotAssignmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FlowerpotAssignmentQueryServiceImpl implements FlowerpotAssignmentQueryService {
    private final FlowerpotAssignmentRepository flowerpotAssignmentRepository;

    public FlowerpotAssignmentQueryServiceImpl(FlowerpotAssignmentRepository flowerpotAssignmentRepository) {
        this.flowerpotAssignmentRepository = flowerpotAssignmentRepository;
    }

    @Override
    public Optional<FlowerpotAssignment> handle(GetFlowerpotAssignmentsByIdQuery query) {
        return flowerpotAssignmentRepository.findById(query.flowerpotAssignmentId());
    }

    @Override
    public List<FlowerpotAssignment> handle(GetAllFlowerpotAssignmentsQuery query) {
        return flowerpotAssignmentRepository.findAll();
    }

    @Override
    public List<Long> handle(GetFlowerpotIdsByPlantOwnerIdQuery query) {
        List<FlowerpotAssignment> flowerpotAssignmentsByPlantOwnerId = flowerpotAssignmentRepository.findByPlantOwnerId(query.plantOwnerId());

        List<Long> flowerpotIds = flowerpotAssignmentsByPlantOwnerId.stream()
                .map(assignment -> assignment.getFlowerpotId().flowerpotId())
                .collect(Collectors.toList());

        return flowerpotIds;
    }

    @Override
    public Optional<FlowerpotAssignment> handle(GetFlowerpotAssignmentByFlowerpotId query) {
        return flowerpotAssignmentRepository.findByFlowerpotId(query.flowerpotId());
    }

    @Override
    public List<Long> handle(GetPlantTypeIdsByPlantOwnerIdQuery query) {
        List<FlowerpotAssignment> flowerpotAssignmentsByPlantOwnerId = flowerpotAssignmentRepository.findByPlantOwnerId(query.plantOwnerId());

        List<Long> plantTypeIds = flowerpotAssignmentsByPlantOwnerId.stream()
                .map(assignment -> assignment.getPlantTypeId().plantTypeId())
                .collect(Collectors.toList());

        return plantTypeIds;
    }
}
