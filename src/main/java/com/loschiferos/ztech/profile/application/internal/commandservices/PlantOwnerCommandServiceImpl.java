package com.loschiferos.ztech.profile.application.internal.commandservices;

import com.loschiferos.ztech.profile.domain.model.aggregates.PlantOwner;
import com.loschiferos.ztech.profile.domain.model.commands.CreatePlantOwnerCommand;
import com.loschiferos.ztech.profile.domain.model.valueobjects.Profile;
import com.loschiferos.ztech.profile.domain.services.PlantOwnerCommandService;
import com.loschiferos.ztech.profile.infrastructure.persistence.jpa.repositories.PlantOwnerRepository;
import com.loschiferos.ztech.shared.domain.exceptions.ValidationException;
import org.springframework.stereotype.Service;

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

        Profile profile = new Profile(command.name(), command.email(), command.password(), command.address(), command.phone(), command.photo());
        PlantOwner plantOwner = new PlantOwner(profile, command.dni(), command.birthday(), command.gender());
        PlantOwner savedPlantOwner = plantOwnerRepository.save(plantOwner);
        return savedPlantOwner.getId();
    }

    private void verifyEmptyFieldsInCreatePlantOwner(CreatePlantOwnerCommand command) {
        if (command.name().isBlank()) {
            throw new ValidationException("Name cannot be blank");
        }
        if (command.email().isBlank()) {
            throw new ValidationException("Email cannot be blank");
        }
        if (command.password().isBlank()) {
            throw new ValidationException("Password cannot be blank");
        }
    }

    private void verifyValidateFieldsInCreatePlantOwner(CreatePlantOwnerCommand command) {
        if (plantOwnerRepository.existsByProfile_Email(command.email())) {
            throw new ValidationException("Plant owner with same email already exists");
        }
        if (plantOwnerRepository.existsByDni(command.dni())) {
            throw new ValidationException("Plant owner with same dni already exists");
        }
        if (command.phone() < 900000000 || command.phone() > 999999999) {
            throw new ValidationException("Phone number is invalid");
        }
        if (plantOwnerRepository.existsByProfile_Phone(command.phone())) {
            throw new ValidationException("Plant owner with same phone already exists");
        }
        if (!command.gender().equals("male")) {
            if (!command.gender().equals("female")) {
                throw new ValidationException("Gender is invalid");
            }
        }
    }
}
