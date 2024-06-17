package com.loschiferos.ztech.pot.interfaces.rest;

import com.loschiferos.ztech.pot.domain.model.entities.Parameter;
import com.loschiferos.ztech.pot.domain.model.queries.GetAllPlantTypesQuery;
import com.loschiferos.ztech.pot.domain.model.queries.GetParametersByPlantTypeIdQuery;
import com.loschiferos.ztech.pot.domain.model.queries.GetPlantTypeByIdQuery;
import com.loschiferos.ztech.pot.domain.model.queries.GetPlantTypeByNameQuery;
import com.loschiferos.ztech.pot.domain.services.PlantTypeCommandService;
import com.loschiferos.ztech.pot.domain.services.PlantTypeQueryService;
import com.loschiferos.ztech.pot.interfaces.rest.resources.*;
import com.loschiferos.ztech.pot.interfaces.rest.transform.*;
import com.loschiferos.ztech.shared.domain.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "**" , maxAge = 3600)
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

    @GetMapping
    public ResponseEntity<List<PlantTypeResource>> getAllPlantTypes() {
        var getAllPlantTypesQuery = new GetAllPlantTypesQuery();
        var plantTypes = plantTypeQueryService.handle(getAllPlantTypesQuery);
        if(plantTypes.isEmpty()) {
            throw new ResourceNotFoundException("Plant types not found");
        }
        var plantTypeResources = plantTypes.stream().map(PlantTypeResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(plantTypeResources);
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

    @PostMapping("/name")
    public ResponseEntity<PlantTypeResource> getPlantTypeByName(@RequestBody String plantTypeName) {
        var getPlantTypeByNameQuery = new GetPlantTypeByNameQuery(plantTypeName);
        var plantType = plantTypeQueryService.handle(getPlantTypeByNameQuery);
        if(plantType.isEmpty()) {
            throw new ResourceNotFoundException("Plant type not found");
        }
        var plantTypeResource = PlantTypeResourceFromEntityAssembler.toResourceFromEntity(plantType.get());
        return ResponseEntity.ok(plantTypeResource);
    }

    @PostMapping("/parameters")
    public ResponseEntity<Void> createParameter(@RequestBody CreateParameterResource resource) {
        var addParameterCommand = CreateParameterCommandFromResourceAssembler.toCommandFromResource(resource);
        plantTypeCommandService.handle(addParameterCommand);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{plantTypeId}/parameters")
    public ResponseEntity<List<ParameterResource>> getParametersByPlantTypeId(@PathVariable Long plantTypeId) {
        var getParametersByPlantTypeIdQuery = new GetParametersByPlantTypeIdQuery(plantTypeId);
        var parameters = plantTypeQueryService.handle(getParametersByPlantTypeIdQuery);
        if (parameters == null) {
            throw new ResourceNotFoundException("Parameters not found");
        }
        var parameterResources = parameters.stream().map(ParameterResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(parameterResources);
    }

    @GetMapping("/{plantTypeId}/details")
    public ResponseEntity<PlantTypeDetailsResource> getPlantTypeDetails(@PathVariable Long plantTypeId) {
        var getPlantTypeByIdQuery = new GetPlantTypeByIdQuery(plantTypeId);
        var plantType = plantTypeQueryService.handle(getPlantTypeByIdQuery);
        if (plantType.isEmpty()) {
            throw new ResourceNotFoundException("Plant type not found");
        }
        var plantTypeResource = PlantTypeResourceFromEntityAssembler.toResourceFromEntity(plantType.get());
        var getParametersByPlantTypeIdQuery = new GetParametersByPlantTypeIdQuery(plantTypeId);
        var parameters = plantTypeQueryService.handle(getParametersByPlantTypeIdQuery);
        if (parameters == null) {
            throw new ResourceNotFoundException("Parameters not found");
        }
        var parameterResources = parameters.stream().map(ParameterResourceFromEntityAssembler::toResourceFromEntity).toList();
        var plantTypeDetailsResource = PlantTypeDetailsResourceAssembler.toResource(plantTypeResource, parameterResources);

        return ResponseEntity.ok(plantTypeDetailsResource);
    }
}
