package com.loschiferos.ztech.profile.interfaces.acl;

import com.loschiferos.ztech.profile.domain.model.aggregates.PlantOwner;
import com.loschiferos.ztech.profile.domain.model.commands.CreatePlantOwnerCommand;
import com.loschiferos.ztech.profile.domain.model.queries.GetPlantOwnerByIdQuery;
import com.loschiferos.ztech.profile.domain.services.PlantOwnerCommandService;
import com.loschiferos.ztech.profile.domain.services.PlantOwnerQueryService;
import org.springframework.stereotype.Service;

import java.util.Date;

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

    public Long savePlantOwner(String name, String email, String address, Long phone, String photo, Long dni, Date birthday, String gender) {
        var createPlantOwnerCommand = new CreatePlantOwnerCommand(name, email, address, phone, photo, dni, birthday, gender);
        var plantOwnerId = plantOwnerCommandService.handle(createPlantOwnerCommand);
        if(plantOwnerId == 0) return 0L;
        return plantOwnerId;
    }
}
