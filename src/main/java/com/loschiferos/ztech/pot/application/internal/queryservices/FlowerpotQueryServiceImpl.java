package com.loschiferos.ztech.pot.application.internal.queryservices;

import com.loschiferos.ztech.pot.domain.model.aggregates.Flowerpot;
import com.loschiferos.ztech.pot.domain.model.entities.Sensor;
import com.loschiferos.ztech.pot.domain.model.queries.GetFlowerpotByCodeQuery;
import com.loschiferos.ztech.pot.domain.model.queries.GetFlowerpotByIdQuery;
import com.loschiferos.ztech.pot.domain.model.queries.GetSensorsByFlowerpotIdQuery;
import com.loschiferos.ztech.pot.domain.services.FlowerpotQueryService;
import com.loschiferos.ztech.pot.infrastructure.persistance.jpa.repositories.FlowerpotRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FlowerpotQueryServiceImpl implements FlowerpotQueryService {

    private final FlowerpotRepository flowerpotRepository;

    public FlowerpotQueryServiceImpl(FlowerpotRepository flowerpotRepository) {
        this.flowerpotRepository = flowerpotRepository;
    }

    @Override
    public Optional<Flowerpot> handle(GetFlowerpotByIdQuery query) {
        return flowerpotRepository.findById(query.flowerpotId());
    }

    @Override
    public Optional<Flowerpot> handle(GetFlowerpotByCodeQuery query) {
        return flowerpotRepository.findByCode(query.code());
    }

    @Override
    public List<Sensor> handle(GetSensorsByFlowerpotIdQuery query) {
        return flowerpotRepository.findById(query.flowerpotId()).get().getAllSensors();
    }
}
