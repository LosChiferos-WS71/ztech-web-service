package com.loschiferos.ztech.loan.infrastructure.persistence.jpa.repositories;

import com.loschiferos.ztech.loan.domain.model.aggregates.FlowerpotAssignment;
import com.loschiferos.ztech.loan.domain.model.valueobjects.FlowerpotId;
import com.loschiferos.ztech.loan.domain.model.valueobjects.PlantOwnerId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FlowerpotAssignmentRepository extends JpaRepository<FlowerpotAssignment, Long> {
    List<FlowerpotAssignment> findByPlantOwnerId(PlantOwnerId plantOwnerId);
    Optional<FlowerpotAssignment> findByFlowerpotId(FlowerpotId flowerpotId);
}
