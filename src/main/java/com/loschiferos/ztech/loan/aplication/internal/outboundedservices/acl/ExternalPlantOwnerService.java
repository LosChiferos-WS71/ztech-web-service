package com.loschiferos.ztech.loan.aplication.internal.outboundedservices.acl;

import com.loschiferos.ztech.loan.domain.model.valueobjects.PlantOwnerId;
import com.loschiferos.ztech.profile.interfaces.acl.PlantOwnerContextFacade;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExternalPlantOwnerService {
    private final PlantOwnerContextFacade plantOwnerContextFacade;

    public ExternalPlantOwnerService(PlantOwnerContextFacade plantOwnerContextFacade) {
        this.plantOwnerContextFacade = plantOwnerContextFacade;
    }

    public Optional<PlantOwnerId> fetchPlantOwnerIdById(Long id) {
        var plantOwnerId = plantOwnerContextFacade.getPlantOwnerIdById(id);
        if (plantOwnerId == 0L) return Optional.empty();
        return Optional.of(new PlantOwnerId(plantOwnerId));
    }
}
