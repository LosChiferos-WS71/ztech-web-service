package com.loschiferos.ztech.profile.application.internal.commandservices;

import com.loschiferos.ztech.profile.domain.model.aggregates.PlantOwner;
import com.loschiferos.ztech.profile.domain.model.commands.CreatePlantOwnerCommand;
import com.loschiferos.ztech.profile.domain.model.commands.UpdatePlantOwnerCommand;
import com.loschiferos.ztech.profile.domain.model.valueobjects.Profile;
import com.loschiferos.ztech.profile.domain.services.PlantOwnerCommandService;
import com.loschiferos.ztech.profile.infrastructure.persistence.jpa.repositories.PlantOwnerRepository;
import com.loschiferos.ztech.shared.domain.exceptions.ResourceNotFoundException;
import com.loschiferos.ztech.shared.domain.exceptions.ValidationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PlantOwnerCommandServiceImpl implements PlantOwnerCommandService {
    private final PlantOwnerRepository plantOwnerRepository;

    public PlantOwnerCommandServiceImpl(PlantOwnerRepository plantOwnerRepository) {
        this.plantOwnerRepository = plantOwnerRepository;
    }

    @Override
    public Long handle(CreatePlantOwnerCommand command) {
        verifyEmptyFieldsInCreatePlantOwner(command);
        verifyValidateFieldsInCreatePlantOwner(command);

        Profile profile = new Profile(command.name(), command.email(), command.address(), command.phone(), command.photo());
        PlantOwner plantOwner = new PlantOwner(profile, command.dni(), command.birthday(), command.gender());
        PlantOwner savedPlantOwner = plantOwnerRepository.save(plantOwner);
        return savedPlantOwner.getId();
    }

    @Override
    public Optional<PlantOwner> handle(UpdatePlantOwnerCommand command) {
        if (!plantOwnerRepository.existsById(command.id())) throw new ResourceNotFoundException("Plant owner not found");
        var plantOwnerToUpdate = plantOwnerRepository.findById(command.id()).get();
        var updatedPlantOwner = plantOwnerRepository.save(plantOwnerToUpdate.updatePlantOwner(command.name(), command.email(), command.address(), command.phone(), command.photo(), command.dni(), command.birthday(), command.gender()));
        return Optional.of(updatedPlantOwner);
    }

    private void verifyEmptyFieldsInCreatePlantOwner(CreatePlantOwnerCommand command) {
        if (command.name().isBlank()) {
            throw new ValidationException("Name cannot be blank");
        }
        if (command.email().isBlank()) {
            throw new ValidationException("Email cannot be blank");
        }
    }

    private void verifyValidateFieldsInCreatePlantOwner(CreatePlantOwnerCommand command) {
        if (plantOwnerRepository.existsByProfile_Email(command.email())) {
            throw new ValidationException("Plant owner with same email already exists");
        }
    }
}
