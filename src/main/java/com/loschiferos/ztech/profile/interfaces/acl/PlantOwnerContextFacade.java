package com.loschiferos.ztech.profile.interfaces.acl;

import com.loschiferos.ztech.profile.domain.model.queries.GetPlantOwnerByIdQuery;
import com.loschiferos.ztech.profile.domain.services.PlantOwnerCommandService;
import com.loschiferos.ztech.profile.domain.services.PlantOwnerQueryService;
import org.springframework.stereotype.Service;

@Service
public class PlantOwnerContextFacade {
    private final PlantOwnerCommandService plantOwnerCommandService;
    private final PlantOwnerQueryService plantOwnerQueryService;

    public PlantOwnerContextFacade(PlantOwnerCommandService plantOwnerCommandService, PlantOwnerQueryService plantOwnerQueryService) {
        this.plantOwnerCommandService = plantOwnerCommandService;
        this.plantOwnerQueryService = plantOwnerQueryService;
    }

    public Long getPlantOwnerIdById(Long Id) {
        var getPlantOwnerByIdQuery = new GetPlantOwnerByIdQuery(Id);
        var plantOwner = plantOwnerQueryService.handle(getPlantOwnerByIdQuery);
        if (plantOwner.isEmpty()) return 0L;
        return plantOwner.get().getId();
    }
}
