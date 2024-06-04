package com.loschiferos.ztech.profile.interfaces.rest;

import com.loschiferos.ztech.profile.domain.model.queries.GetAllPlantOwnersQuery;
import com.loschiferos.ztech.profile.domain.model.queries.GetPlantOwnerByIdQuery;
import com.loschiferos.ztech.profile.domain.services.PlantOwnerCommandService;
import com.loschiferos.ztech.profile.domain.services.PlantOwnerQueryService;
import com.loschiferos.ztech.profile.interfaces.rest.resources.CreatePlantOwnerResource;
import com.loschiferos.ztech.profile.interfaces.rest.resources.PlantOwnerResource;
import com.loschiferos.ztech.profile.interfaces.rest.transform.CreatePlantOwnerCommandFromResourceAssembler;
import com.loschiferos.ztech.profile.interfaces.rest.transform.PlantOwnerResourceFromEntityAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/plant-owners")
public class PlantOwnerController {
    private final PlantOwnerCommandService plantOwnerCommandService;
    private final PlantOwnerQueryService plantOwnerQueryService;

    @Autowired
    public PlantOwnerController(PlantOwnerCommandService plantOwnerCommandService, PlantOwnerQueryService plantOwnerQueryService) {
        this.plantOwnerCommandService = plantOwnerCommandService;
        this.plantOwnerQueryService = plantOwnerQueryService;
    }

    @PostMapping
    public ResponseEntity<PlantOwnerResource> createSupplier(@RequestBody CreatePlantOwnerResource resource) {
        var createPlantOwnerCommand = CreatePlantOwnerCommandFromResourceAssembler.toCommandFromResource(resource);
        var plantOwnerId = plantOwnerCommandService.handle(createPlantOwnerCommand);
        if (plantOwnerId == 0L) {
            return ResponseEntity.badRequest().build();
        }
        var getPlantOwnerByIdQuery = new GetPlantOwnerByIdQuery(plantOwnerId);
        var plantOwner = plantOwnerQueryService.handle(getPlantOwnerByIdQuery);

        if (plantOwner.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        var plantOwnerResource = PlantOwnerResourceFromEntityAssembler.toResourceFromEntity(plantOwner.get());
        return new ResponseEntity<>(plantOwnerResource, HttpStatus.CREATED);
    }

    @GetMapping("/{plantOwnerId}")
    public ResponseEntity<PlantOwnerResource> getPlantOwnerById(@PathVariable Long plantOwnerId) {
        var getPlantOwnerByIdQuery = new GetPlantOwnerByIdQuery(plantOwnerId);
        var plantOwner = plantOwnerQueryService.handle(getPlantOwnerByIdQuery);
        if (plantOwner.isEmpty()) return ResponseEntity.badRequest().build();
        var plantOwnerResource = PlantOwnerResourceFromEntityAssembler.toResourceFromEntity(plantOwner.get());
        return ResponseEntity.ok(plantOwnerResource);
    }

    @GetMapping
    public ResponseEntity<List<PlantOwnerResource>> getAllPlantOwners() {
        var getAllPlantOwnersQuery = new GetAllPlantOwnersQuery();
        var plantOwners = plantOwnerQueryService.handle(getAllPlantOwnersQuery);
        var plantOwnerResources = plantOwners.stream()
                .map(PlantOwnerResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(plantOwnerResources);
    }

    @PutMapping
    public ResponseEntity<PlantOwnerResource> updatePlantOwner(@RequestBody CreatePlantOwnerResource resource) {
        var createPlantOwnerCommand = CreatePlantOwnerCommandFromResourceAssembler.toCommandFromResource(resource);
        var plantOwnerId = plantOwnerCommandService.handle(createPlantOwnerCommand);
        if (plantOwnerId == 0L) {
            return ResponseEntity.badRequest().build();
        }
        var getPlantOwnerByIdQuery = new GetPlantOwnerByIdQuery(plantOwnerId);
        var plantOwner = plantOwnerQueryService.handle(getPlantOwnerByIdQuery);

        if (plantOwner.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        var plantOwnerResource = PlantOwnerResourceFromEntityAssembler.toResourceFromEntity(plantOwner.get());
        return new ResponseEntity<>(plantOwnerResource, HttpStatus.CREATED);
    }
}
