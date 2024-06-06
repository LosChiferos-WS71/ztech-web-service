package com.loschiferos.ztech.loan.aplication.internal.outboundedservices.acl;

import com.loschiferos.ztech.loan.domain.model.valueobjects.PlantTypeId;
import com.loschiferos.ztech.pot.interfaces.acl.PlantTypeContextFacade;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExternalPlantTypeService {
    private final PlantTypeContextFacade plantTypeContextFacade;

    public ExternalPlantTypeService(PlantTypeContextFacade plantTypeContextFacade) {
        this.plantTypeContextFacade = plantTypeContextFacade;
    }

    public Optional<PlantTypeId> fetchPlantTypeIdById(Long Id) {
        var plantTypeId = plantTypeContextFacade.getPlantTypeIdById(Id);
        if (plantTypeId == 0L) return Optional.empty();
        return Optional.of(new PlantTypeId(plantTypeId));
    }
}
