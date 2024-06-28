package com.loschiferos.ztech.iam.application.internal.outboundservices.acl;

import com.loschiferos.ztech.iam.domain.model.valueobjects.PlantOwnerId;
import com.loschiferos.ztech.profile.interfaces.acl.PlantOwnerContextFacade;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service("externalPlantOwnerServiceIam")
public class ExternalPlantOwnerService {
    private final PlantOwnerContextFacade plantOwnerContextFacade;
    public ExternalPlantOwnerService(PlantOwnerContextFacade plantOwnerContextFacade) {
        this.plantOwnerContextFacade = plantOwnerContextFacade;
    }

    public Optional<PlantOwnerId> savePlantOwner(String name, String email, String address, Long phone, String photo, Long dni, Date birthday, String gender) {
        var savedPlantOwnerId = plantOwnerContextFacade.savePlantOwner(name, email, address, phone, photo, dni, birthday, gender);
        if (savedPlantOwnerId == 0) return Optional.empty();
        return Optional.of(new PlantOwnerId(savedPlantOwnerId));
    }
}
