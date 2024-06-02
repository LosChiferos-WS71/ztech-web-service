package com.loschiferos.ztech.pot.application.internal.commandservices;

import com.loschiferos.ztech.pot.domain.model.aggregates.Flowerpot;
import com.loschiferos.ztech.pot.domain.model.commands.CreateFlowerpotCommand;
import com.loschiferos.ztech.pot.domain.model.valueobjects.SensorsData;
import com.loschiferos.ztech.pot.domain.services.FlowerpotCommandService;
import com.loschiferos.ztech.pot.infrastructure.persistance.jpa.repositories.FlowerpotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FlowerpotCommandServiceImpl implements FlowerpotCommandService {

    private final FlowerpotRepository flowerpotRepository;

    @Autowired
    public FlowerpotCommandServiceImpl(FlowerpotRepository flowerpotRepository) {
        this.flowerpotRepository = flowerpotRepository;
    }

    @Override
    public Long handle(CreateFlowerpotCommand command) {
        if (flowerpotRepository.existsByCode(command.code())) {
            throw new IllegalArgumentException("Flowerpot with same title already exists");
        }
        SensorsData sensorsData = new SensorsData(command.lastTemperature(), command.lastHumidity(), command.lastSunlight());
        Flowerpot flowerpot = new Flowerpot(command.code(), command.active(), sensorsData);
        Flowerpot savedFlowerpot = flowerpotRepository.save(flowerpot);
        return savedFlowerpot.getId();
    }
}
