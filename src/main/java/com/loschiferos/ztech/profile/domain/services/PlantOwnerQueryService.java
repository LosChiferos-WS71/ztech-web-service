package com.loschiferos.ztech.profile.domain.services;


import com.loschiferos.ztech.profile.domain.model.aggregates.PlantOwner;
import com.loschiferos.ztech.profile.domain.model.queries.GetAllPlantOwnersQuery;
import com.loschiferos.ztech.profile.domain.model.queries.GetPlantOwnerByEmailQuery;
import com.loschiferos.ztech.profile.domain.model.queries.GetPlantOwnerByIdQuery;

import java.util.List;
import java.util.Optional;

public interface PlantOwnerQueryService {
    Optional<PlantOwner> handle(GetPlantOwnerByIdQuery query);
    List<PlantOwner> handle(GetAllPlantOwnersQuery query);
    Optional<PlantOwner> handle(GetPlantOwnerByEmailQuery query);
}
