package com.loschiferos.ztech.loan.infrastructure.persistence.jpa.repositories;

import com.loschiferos.ztech.loan.domain.model.aggregates.FlowerpotAssignment;
import com.loschiferos.ztech.loan.domain.model.valueobjects.PlantOwnerId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlowerpotAssignmentRepository extends JpaRepository<FlowerpotAssignment, Long> {
    List<FlowerpotAssignment> findByPlantOwnerId(PlantOwnerId plantOwnerId);
}
