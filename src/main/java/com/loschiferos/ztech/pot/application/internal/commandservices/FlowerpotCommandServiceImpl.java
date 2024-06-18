package com.loschiferos.ztech.pot.application.internal.commandservices;

import com.loschiferos.ztech.pot.domain.model.aggregates.Flowerpot;
import com.loschiferos.ztech.pot.domain.model.commands.CreateFlowerpotCommand;
import com.loschiferos.ztech.pot.domain.model.commands.CreateSensorCommand;
import com.loschiferos.ztech.pot.domain.model.valueobjects.SensorType;
import com.loschiferos.ztech.pot.domain.model.valueobjects.SensorsData;
import com.loschiferos.ztech.pot.domain.services.FlowerpotCommandService;
import com.loschiferos.ztech.pot.infrastructure.persistance.jpa.repositories.FlowerpotRepository;
import com.loschiferos.ztech.shared.domain.exceptions.ResourceNotFoundException;
import com.loschiferos.ztech.shared.domain.exceptions.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class FlowerpotCommandServiceImpl implements FlowerpotCommandService {

    private final FlowerpotRepository flowerpotRepository;

    @Autowired
    public FlowerpotCommandServiceImpl(FlowerpotRepository flowerpotRepository) {
        this.flowerpotRepository = flowerpotRepository;
    }

    @Override
    public Long handle(CreateFlowerpotCommand command) {
        if (command.code() == null || command.code().isEmpty()) {
            throw new ValidationException("Code cannot be empty");
        }
        if (flowerpotRepository.existsByCode(command.code())) {
            throw new ValidationException("Flowerpot with same code already exists");
        }
        if (command.code().length() != 5) {
            throw new ValidationException("Code must be 5 characters long");
        }

        var codePattern = command.code().toLowerCase();
        SensorsData sensorsData = new SensorsData(command.lastTemperature(), command.lastHumidity(), command.lastSunlight());
        Flowerpot flowerpot = new Flowerpot(codePattern, command.active(), sensorsData);
        Flowerpot savedFlowerpot = flowerpotRepository.save(flowerpot);
        return savedFlowerpot.getId();
    }

    @Override
    public void handle(CreateSensorCommand command) {
        Flowerpot flowerpot = flowerpotRepository.findById(command.flowerpotId())
                .orElseThrow(() -> new ResourceNotFoundException("Flowerpot not found"));

        validateCreateSensorCommand(command);

        var sensorType = SensorType.fromValue(command.type());

        flowerpot.createSensor(sensorType, command.value());
        flowerpotRepository.save(flowerpot);
    }

    private void validateCreateSensorCommand(CreateSensorCommand command) {
        if (command.type() == null) {
            throw new ValidationException("Type cannot be null");
        }
        if (command.value() == null) {
            throw new ValidationException("Value cannot be null");
        }
    }
}