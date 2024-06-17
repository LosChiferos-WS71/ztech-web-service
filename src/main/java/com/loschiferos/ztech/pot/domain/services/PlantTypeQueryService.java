package com.loschiferos.ztech.pot.domain.services;

import com.loschiferos.ztech.pot.domain.model.aggregates.PlantType;
import com.loschiferos.ztech.pot.domain.model.entities.Parameter;
import com.loschiferos.ztech.pot.domain.model.queries.GetAllPlantTypesQuery;
import com.loschiferos.ztech.pot.domain.model.queries.GetParametersByPlantTypeIdQuery;
import com.loschiferos.ztech.pot.domain.model.queries.GetPlantTypeByIdQuery;
import com.loschiferos.ztech.pot.domain.model.queries.GetPlantTypeByNameQuery;
import com.loschiferos.ztech.pot.domain.model.valueobjects.ParameterList;

import java.util.List;
import java.util.Optional;

public interface PlantTypeQueryService {
    Optional<PlantType> handle(GetPlantTypeByIdQuery query);
    List<PlantType> handle(GetAllPlantTypesQuery query);
    Optional<PlantType> handle(GetPlantTypeByNameQuery query);
    List<Parameter> handle(GetParametersByPlantTypeIdQuery query);
}
