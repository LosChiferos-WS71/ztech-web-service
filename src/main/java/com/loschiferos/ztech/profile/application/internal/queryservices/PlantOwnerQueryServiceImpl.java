package com.loschiferos.ztech.profile.application.internal.queryservices;

import com.loschiferos.ztech.profile.domain.model.aggregates.PlantOwner;
import com.loschiferos.ztech.profile.domain.model.queries.GetAllPlantOwnersQuery;
import com.loschiferos.ztech.profile.domain.model.queries.GetPlantOwnerByEmailQuery;
import com.loschiferos.ztech.profile.domain.model.queries.GetPlantOwnerByIdQuery;
import com.loschiferos.ztech.profile.domain.services.PlantOwnerQueryService;
import com.loschiferos.ztech.profile.infrastructure.persistence.jpa.repositories.PlantOwnerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlantOwnerQueryServiceImpl implements PlantOwnerQueryService {
    private final PlantOwnerRepository plantOwnerRepository;

    public PlantOwnerQueryServiceImpl(PlantOwnerRepository plantOwnerRepository) {
        this.plantOwnerRepository = plantOwnerRepository;
    }

    @Override
    public Optional<PlantOwner> handle(GetPlantOwnerByIdQuery query) {
        return plantOwnerRepository.findById(query.id());
    }

    @Override
    public List<PlantOwner> handle(GetAllPlantOwnersQuery query) {
        return plantOwnerRepository.findAll();
    }

    @Override
    public Optional<PlantOwner> handle(GetPlantOwnerByEmailQuery query) {
        return plantOwnerRepository.findByProfile_Email(query.email());
    }
}
