package com.loschiferos.ztech.claim.infraestructure.persistence.jpa.repositories;

import com.loschiferos.ztech.claim.domain.model.aggregates.FlowerpotClaimRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlowerpotClaimRequestRepository extends JpaRepository<FlowerpotClaimRequest, Long> {
    //boolean existsByCode(String code);
}
