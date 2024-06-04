package com.loschiferos.ztech.pot.interfaces.rest;

import com.loschiferos.ztech.pot.domain.model.queries.GetFlowerpotByIdQuery;
import com.loschiferos.ztech.pot.domain.model.queries.GetPlantTypeByIdQuery;
import com.loschiferos.ztech.pot.domain.services.PlantTypeCommandService;
import com.loschiferos.ztech.pot.domain.services.PlantTypeQueryService;
import com.loschiferos.ztech.pot.interfaces.rest.resources.CreatePlantTypeResource;
import com.loschiferos.ztech.pot.interfaces.rest.resources.FlowerpotResource;
import com.loschiferos.ztech.pot.interfaces.rest.resources.PlantTypeResource;
import com.loschiferos.ztech.pot.interfaces.rest.transform.CreatePlantTypeCommandFromResourceAssembler;
import com.loschiferos.ztech.pot.interfaces.rest.transform.FlowerpotResourceFromEntityAssembler;
import com.loschiferos.ztech.pot.interfaces.rest.transform.PlantTypeResourceFromEntityAssembler;
import com.loschiferos.ztech.shared.domain.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/plant/types")
public class PlantTypeController {

    private final PlantTypeCommandService plantTypeCommandService;
    private final PlantTypeQueryService plantTypeQueryService;

    @Autowired
    public PlantTypeController(PlantTypeCommandService plantTypeCommandService, PlantTypeQueryService plantTypeQueryService) {
        this.plantTypeCommandService = plantTypeCommandService;
        this.plantTypeQueryService = plantTypeQueryService;
    }

    @PostMapping
    public ResponseEntity<PlantTypeResource> createPlantType(@RequestBody CreatePlantTypeResource resource) {
        var createPlantTypeCommand = CreatePlantTypeCommandFromResourceAssembler.toCommandFromResource(resource);
        var plantTypeId = plantTypeCommandService.handle(createPlantTypeCommand);
        if(plantTypeId == 0L) {
            return ResponseEntity.badRequest().build();
        }
        var getPlantTypeByIdQuery = new GetPlantTypeByIdQuery(plantTypeId);
        var plantType = plantTypeQueryService.handle(getPlantTypeByIdQuery);
        if(plantType.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var plantTypeResource = PlantTypeResourceFromEntityAssembler.toResourceFromEntity(plantType.get());
        return new ResponseEntity<>(plantTypeResource, HttpStatus.CREATED);
    }

    @GetMapping("/{plantTypeId}")
    public ResponseEntity<PlantTypeResource> getPlantTypeById(@PathVariable Long plantTypeId) {
        var getPlantTypeByIdQuery = new GetPlantTypeByIdQuery(plantTypeId);
        var plantType = plantTypeQueryService.handle(getPlantTypeByIdQuery);
        if(plantType.isEmpty()) {
            throw new ResourceNotFoundException("Plant type not found");
        }
        var plantTypeResource = PlantTypeResourceFromEntityAssembler.toResourceFromEntity(plantType.get());
        return ResponseEntity.ok(plantTypeResource);
    }
}
