package com.loschiferos.ztech.pot.domain.services;

import com.loschiferos.ztech.pot.domain.model.aggregates.Flowerpot;
import com.loschiferos.ztech.pot.domain.model.entities.Sensor;
import com.loschiferos.ztech.pot.domain.model.queries.GetFlowerpotByCodeQuery;
import com.loschiferos.ztech.pot.domain.model.queries.GetFlowerpotByIdQuery;
import com.loschiferos.ztech.pot.domain.model.queries.GetSensorsByFlowerpotIdQuery;

import java.util.List;
import java.util.Optional;

public interface FlowerpotQueryService {
    Optional<Flowerpot> handle(GetFlowerpotByIdQuery query);
    Optional<Flowerpot> handle(GetFlowerpotByCodeQuery query);
    List<Sensor> handle(GetSensorsByFlowerpotIdQuery query);
}
