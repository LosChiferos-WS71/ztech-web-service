package com.loschiferos.ztech.pot.domain.services;

import com.loschiferos.ztech.pot.domain.model.aggregates.Flowerpot;
import com.loschiferos.ztech.pot.domain.model.queries.GetFlowerpotByIdQuery;

import java.util.List;
import java.util.Optional;

public interface FlowerpotQueryService {
    Optional<Flowerpot> handle(GetFlowerpotByIdQuery query);
}
