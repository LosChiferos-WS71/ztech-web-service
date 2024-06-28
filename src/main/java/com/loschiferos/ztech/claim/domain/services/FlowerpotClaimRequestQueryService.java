package com.loschiferos.ztech.claim.domain.services;

import com.loschiferos.ztech.claim.domain.model.aggregates.FlowerpotClaimRequest;
import com.loschiferos.ztech.claim.domain.model.queries.GetFlowerpotClaimRequestByIdQuery;

import java.util.Optional;

public interface FlowerpotClaimRequestQueryService {
    Optional<FlowerpotClaimRequest> handle(GetFlowerpotClaimRequestByIdQuery query);
}
