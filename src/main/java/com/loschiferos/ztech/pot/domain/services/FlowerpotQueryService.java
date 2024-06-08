package com.loschiferos.ztech.pot.domain.services;

import com.loschiferos.ztech.pot.domain.model.aggregates.Flowerpot;
import com.loschiferos.ztech.pot.domain.model.queries.GetFlowerpotByCodeQuery;
import com.loschiferos.ztech.pot.domain.model.queries.GetFlowerpotByIdQuery;

import java.util.Optional;

public interface FlowerpotQueryService {
    Optional<Flowerpot> handle(GetFlowerpotByIdQuery query);
    Optional<Flowerpot> handle(GetFlowerpotByCodeQuery query);
}
