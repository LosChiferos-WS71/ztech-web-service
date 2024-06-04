package com.loschiferos.ztech.pot.application.internal.commandservices;

import com.loschiferos.ztech.pot.domain.model.aggregates.PlantType;
import com.loschiferos.ztech.pot.domain.model.commands.CreatePlantTypeCommand;
import com.loschiferos.ztech.pot.domain.services.PlantTypeCommandService;
import com.loschiferos.ztech.pot.infrastructure.persistance.jpa.repositories.PlantTypeRepository;
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
        if (command.description() == null || command.description().isEmpty()) {
            throw new ValidationException("Description cannot be empty");
        }

        PlantType plantType = new PlantType(command.name(), command.description());
        PlantType savedPlantType = plantTypeRepository.save(plantType);
        return savedPlantType.getId();
    }
}
