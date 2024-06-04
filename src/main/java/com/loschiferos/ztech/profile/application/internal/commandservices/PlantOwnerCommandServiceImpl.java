package com.loschiferos.ztech.profile.application.internal.commandservices;

import com.loschiferos.ztech.pot.domain.model.aggregates.Flowerpot;
import com.loschiferos.ztech.pot.domain.model.valueobjects.SensorsData;
import com.loschiferos.ztech.profile.domain.model.aggregates.PlantOwner;
import com.loschiferos.ztech.profile.domain.model.commands.CreatePlantOwnerCommand;
import com.loschiferos.ztech.profile.domain.model.valueobjects.Profile;
import com.loschiferos.ztech.profile.domain.services.PlantOwnerCommandService;
import com.loschiferos.ztech.profile.infrastructure.persistence.jpa.repositories.PlantOwnerRepository;
import org.springframework.stereotype.Service;

@Service
public class PlantOwnerCommandServiceImpl implements PlantOwnerCommandService {
    private final PlantOwnerRepository plantOwnerRepository;

    public PlantOwnerCommandServiceImpl(PlantOwnerRepository plantOwnerRepository) {
        this.plantOwnerRepository = plantOwnerRepository;
    }

    @Override
    public Long handle(CreatePlantOwnerCommand command) {
        if (plantOwnerRepository.existsByDni(command.dni())) {
            throw new IllegalArgumentException("Plant owner with same dni already exists");
        }
        Profile profile = new Profile(command.name(), command.email(), command.password(), command.address(), command.phone(), command.photo());
        PlantOwner plantOwner = new PlantOwner(profile, command.dni(), command.birthday(), command.gender());
        PlantOwner savedPlantOwner = plantOwnerRepository.save(plantOwner);
        return savedPlantOwner.getId();
    }
}
