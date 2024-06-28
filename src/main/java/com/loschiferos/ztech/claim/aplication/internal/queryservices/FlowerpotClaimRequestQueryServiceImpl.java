package com.loschiferos.ztech.claim.aplication.internal.queryservices;

import com.loschiferos.ztech.claim.domain.model.aggregates.FlowerpotClaimRequest;
import com.loschiferos.ztech.claim.domain.model.queries.GetFlowerpotClaimRequestByIdQuery;
import com.loschiferos.ztech.claim.domain.services.FlowerpotClaimRequestQueryService;
import com.loschiferos.ztech.claim.infraestructure.persistence.jpa.repositories.FlowerpotClaimRequestRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FlowerpotClaimRequestQueryServiceImpl implements FlowerpotClaimRequestQueryService {

    private final FlowerpotClaimRequestRepository flowerpotClaimRequestRepository;

    public FlowerpotClaimRequestQueryServiceImpl(FlowerpotClaimRequestRepository flowerpotClaimRequestRepository) {
        this.flowerpotClaimRequestRepository = flowerpotClaimRequestRepository;
    }


    @Override
    public Optional<FlowerpotClaimRequest> handle(GetFlowerpotClaimRequestByIdQuery query) {
        return flowerpotClaimRequestRepository.findById(query.flowerpotClaimRequestId());
    }
}
