package com.loschiferos.ztech.loan.infrastructure.persistence.jpa.repositories;

import com.loschiferos.ztech.loan.domain.model.aggregates.FlowerpotAssigment;
import com.loschiferos.ztech.loan.domain.model.valueobjects.FlowerpotId;
import com.loschiferos.ztech.loan.domain.model.valueobjects.PlantOwnerId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface FlowerpotAssigmentRepository extends JpaRepository<FlowerpotAssigment, Long> {
    List<FlowerpotId> findFlowerpotIdsByPlantOwnerId(PlantOwnerId plantOwnerId);
}
