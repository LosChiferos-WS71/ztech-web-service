package com.loschiferos.ztech.pot.interfaces.rest;

import com.loschiferos.ztech.pot.domain.model.aggregates.Flowerpot;
import com.loschiferos.ztech.pot.domain.model.queries.GetFlowerpotByIdQuery;
import com.loschiferos.ztech.pot.domain.services.FlowerpotCommandService;
import com.loschiferos.ztech.pot.domain.services.FlowerpotQueryService;
import com.loschiferos.ztech.pot.interfaces.rest.resources.CreateFlowerpotResource;
import com.loschiferos.ztech.pot.interfaces.rest.resources.FlowerpotResource;
import com.loschiferos.ztech.pot.interfaces.rest.transform.CreateFlowerpotCommandFromResourceAssembler;
import com.loschiferos.ztech.pot.interfaces.rest.transform.FlowerpotResourceFromEntityAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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
            return ResponseEntity.notFound().build();
        }
        var flowerpotResource = FlowerpotResourceFromEntityAssembler.toResourceFromEntity(flowerpot.get());
        return ResponseEntity.ok(flowerpotResource);
    }
}
