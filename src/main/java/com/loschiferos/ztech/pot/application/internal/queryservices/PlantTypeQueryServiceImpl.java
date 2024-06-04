package com.loschiferos.ztech.pot.application.internal.queryservices;

import com.loschiferos.ztech.pot.domain.model.aggregates.PlantType;
import com.loschiferos.ztech.pot.domain.model.queries.GetPlantTypeByIdQuery;
import com.loschiferos.ztech.pot.domain.services.PlantTypeQueryService;
import com.loschiferos.ztech.pot.infrastructure.persistance.jpa.repositories.PlantTypeRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PlantTypeQueryServiceImpl implements PlantTypeQueryService {

    private final PlantTypeRepository plantTypeRepository;

    public PlantTypeQueryServiceImpl(PlantTypeRepository plantTypeRepository) {
        this.plantTypeRepository = plantTypeRepository;
    }

    @Override
    public Optional<PlantType> handle(GetPlantTypeByIdQuery query) {
        return plantTypeRepository.findById(query.plantTypeId());
    }
}
