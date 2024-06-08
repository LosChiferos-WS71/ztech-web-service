package com.loschiferos.ztech.pot.interfaces.rest;

import com.loschiferos.ztech.pot.domain.model.queries.GetFlowerpotByCodeQuery;
import com.loschiferos.ztech.pot.domain.model.queries.GetFlowerpotByIdQuery;
import com.loschiferos.ztech.pot.domain.services.FlowerpotCommandService;
import com.loschiferos.ztech.pot.domain.services.FlowerpotQueryService;
import com.loschiferos.ztech.pot.interfaces.rest.resources.CreateFlowerpotResource;
import com.loschiferos.ztech.pot.interfaces.rest.resources.CreateSensorResource;
import com.loschiferos.ztech.pot.interfaces.rest.resources.FlowerpotResource;
import com.loschiferos.ztech.pot.interfaces.rest.transform.CreateFlowerpotCommandFromResourceAssembler;
import com.loschiferos.ztech.pot.interfaces.rest.transform.CreateSensorCommandFromResourceAssembler;
import com.loschiferos.ztech.pot.interfaces.rest.transform.FlowerpotResourceFromEntityAssembler;
import com.loschiferos.ztech.shared.domain.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "**" , maxAge = 3600)
@RestController
@RequestMapping("/api/v1/flowerpots")
public class FlowerpotController {

    private final FlowerpotCommandService flowerpotCommandService;
    private final FlowerpotQueryService flowerpotQueryService;

    @Autowired
    public FlowerpotController(FlowerpotCommandService flowerpotCommandService, FlowerpotQueryService flowerpotQueryService) {
        this.flowerpotCommandService = flowerpotCommandService;
        this.flowerpotQueryService = flowerpotQueryService;
    }

    @PostMapping
    public ResponseEntity<FlowerpotResource> createFlowerpot(@RequestBody CreateFlowerpotResource resource) {
        var createFlowerpotCommand = CreateFlowerpotCommandFromResourceAssembler.toCommandFromResource(resource);
        var flowerpotId = flowerpotCommandService.handle(createFlowerpotCommand);
        if(flowerpotId == 0L) {
            return ResponseEntity.badRequest().build();
        }
        var getFlowerpotByIdQuery = new GetFlowerpotByIdQuery(flowerpotId);
        var flowerpot = flowerpotQueryService.handle(getFlowerpotByIdQuery);
        if(flowerpot.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var flowerpotResource = FlowerpotResourceFromEntityAssembler.toResourceFromEntity(flowerpot.get());
        return new ResponseEntity<>(flowerpotResource, HttpStatus.CREATED);
    }

    @GetMapping("/{flowerpotId}")
    public ResponseEntity<FlowerpotResource> getFlowerpotById(@PathVariable Long flowerpotId) {
        var getFlowerpotByIdQuery = new GetFlowerpotByIdQuery(flowerpotId);
        var flowerpot = flowerpotQueryService.handle(getFlowerpotByIdQuery);
        if(flowerpot.isEmpty()) {
            throw new ResourceNotFoundException("Flowerpot not found");
        }
        var flowerpotResource = FlowerpotResourceFromEntityAssembler.toResourceFromEntity(flowerpot.get());
        return ResponseEntity.ok(flowerpotResource);
    }

    @PostMapping("/code")
    public ResponseEntity<FlowerpotResource> getFlowerpotByCode(@RequestBody String code) {
        var getFlowerpotByCodeQuery = new GetFlowerpotByCodeQuery(code);
        var flowerpot = flowerpotQueryService.handle(getFlowerpotByCodeQuery);
        if(flowerpot.isEmpty()) {
            throw new ResourceNotFoundException("Flowerpot not found");
        }
        var flowerpotResource = FlowerpotResourceFromEntityAssembler.toResourceFromEntity(flowerpot.get());
        return ResponseEntity.ok(flowerpotResource);
    }

    @PostMapping("/{flowerpotId}/sensors")
    public ResponseEntity<Void> createSensor(@RequestBody CreateSensorResource resource) {
        var addSensorCommand = CreateSensorCommandFromResourceAssembler.toCommandFromResource(resource);
        flowerpotCommandService.handle(addSensorCommand);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
