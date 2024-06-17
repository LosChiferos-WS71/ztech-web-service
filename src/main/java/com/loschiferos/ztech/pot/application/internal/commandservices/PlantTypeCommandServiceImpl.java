package com.loschiferos.ztech.pot.application.internal.commandservices;

import com.loschiferos.ztech.pot.domain.model.aggregates.PlantType;
import com.loschiferos.ztech.pot.domain.model.commands.CreateParameterCommand;
import com.loschiferos.ztech.pot.domain.model.commands.CreatePlantTypeCommand;
import com.loschiferos.ztech.pot.domain.model.valueobjects.ParameterType;
import com.loschiferos.ztech.pot.domain.services.PlantTypeCommandService;
import com.loschiferos.ztech.pot.infrastructure.persistance.jpa.repositories.PlantTypeRepository;
import com.loschiferos.ztech.shared.domain.exceptions.ResourceNotFoundException;
import com.loschiferos.ztech.shared.domain.exceptions.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlantTypeCommandServiceImpl implements PlantTypeCommandService {

    private final PlantTypeRepository plantTypeRepository;

    @Autowired
    public PlantTypeCommandServiceImpl(PlantTypeRepository plantTypeRepository) {
        this.plantTypeRepository = plantTypeRepository;
    }

    @Override
    public Long handle(CreatePlantTypeCommand command) {
        if (command.name() == null || command.name().isEmpty()) {
            throw new ValidationException("Name cannot be empty");
        }
        if (plantTypeRepository.existsByName(command.name())) {
            throw new ValidationException("PlantType with same name already exists");
        }
        if (command.scientificName() == null || command.scientificName().isEmpty()) {
            throw new ValidationException("Scientific name cannot be empty");
        }
        if (command.photo() == null || command.photo().isEmpty()) {
            throw new ValidationException("Photo cannot be empty");
        }
        if (command.description() == null || command.description().isEmpty()) {
            throw new ValidationException("Description cannot be empty");
        }

        PlantType plantType = new PlantType(command.name(), command.scientificName(), command.photo(), command.description());
        PlantType savedPlantType = plantTypeRepository.save(plantType);
        return savedPlantType.getId();
    }

    @Override
    public void handle(CreateParameterCommand command) {
        PlantType plantType = plantTypeRepository.findById(command.plantTypeId())
                .orElseThrow(() -> new ResourceNotFoundException("PlantType not found"));

        validateCreateParameterCommand(command);

        if (plantType.getParameterList().getAllParameterTypes() != null) {
            for (var parameter : plantType.getParameterList().getAllParameterTypes()) {
                if (parameter.equals(ParameterType.fromValue(command.type()))) {
                    throw new ValidationException("Parameter with same type already exists");
                }
            }
        }

        var parameterType = ParameterType.fromValue(command.type());

        plantType.createParameter(parameterType, command.value());
        plantTypeRepository.save(plantType);
    }

    private void validateCreateParameterCommand(CreateParameterCommand command) {
        if (command.type() == null) {
            throw new ValidationException("Type cannot be empty");
        }
        if (command.value() == null || command.value().describeConstable().isEmpty()) {
            throw new ValidationException("Value cannot be empty");
        }
    }
}
