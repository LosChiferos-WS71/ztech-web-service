package com.loschiferos.ztech.pot.application.internal.queryservices;

import com.loschiferos.ztech.pot.domain.model.aggregates.PlantType;
import com.loschiferos.ztech.pot.domain.model.entities.Parameter;
import com.loschiferos.ztech.pot.domain.model.queries.GetAllPlantTypesQuery;
import com.loschiferos.ztech.pot.domain.model.queries.GetParametersByPlantTypeIdQuery;
import com.loschiferos.ztech.pot.domain.model.queries.GetPlantTypeByIdQuery;
import com.loschiferos.ztech.pot.domain.model.queries.GetPlantTypeByNameQuery;
import com.loschiferos.ztech.pot.domain.model.valueobjects.ParameterList;
import com.loschiferos.ztech.pot.domain.services.PlantTypeQueryService;
import com.loschiferos.ztech.pot.infrastructure.persistance.jpa.repositories.PlantTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
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

    @Override
    public List<PlantType> handle(GetAllPlantTypesQuery query) {
        return plantTypeRepository.findAll();
    }

    @Override
    public Optional<PlantType> handle(GetPlantTypeByNameQuery query) {
        return plantTypeRepository.findByName(query.plantTypeName());
    }

    @Override
    public List<Parameter> handle(GetParametersByPlantTypeIdQuery query) {
        return plantTypeRepository.findById(query.plantTypeId()).get().getAllParameters();
    }
}
