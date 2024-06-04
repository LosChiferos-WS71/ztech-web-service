package com.loschiferos.ztech.pot.domain.services;

import com.loschiferos.ztech.pot.domain.model.aggregates.PlantType;
import com.loschiferos.ztech.pot.domain.model.queries.GetPlantTypeByIdQuery;

import java.util.Optional;

public interface PlantTypeQueryService {
    Optional<PlantType> handle(GetPlantTypeByIdQuery query);
}
