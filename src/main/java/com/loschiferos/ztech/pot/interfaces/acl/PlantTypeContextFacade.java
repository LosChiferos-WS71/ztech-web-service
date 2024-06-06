package com.loschiferos.ztech.pot.interfaces.acl;

import com.loschiferos.ztech.pot.domain.model.queries.GetPlantTypeByIdQuery;
import com.loschiferos.ztech.pot.domain.services.PlantTypeCommandService;
import com.loschiferos.ztech.pot.domain.services.PlantTypeQueryService;
import org.springframework.stereotype.Service;

@Service
public class PlantTypeContextFacade {
    private final PlantTypeCommandService plantTypeCommandService;
    private final PlantTypeQueryService plantTypeQueryService;

    public PlantTypeContextFacade(PlantTypeCommandService plantTypeCommandService, PlantTypeQueryService plantTypeQueryService) {
        this.plantTypeCommandService = plantTypeCommandService;
        this.plantTypeQueryService = plantTypeQueryService;
    }

    public Long getPlantTypeIdById(Long Id) {
        var getPlantTypeByIdQuery = new GetPlantTypeByIdQuery(Id);
        var plantType = plantTypeQueryService.handle(getPlantTypeByIdQuery);
        if (plantType.isEmpty()) return 0L;
        return plantType.get().getId();
    }
}
