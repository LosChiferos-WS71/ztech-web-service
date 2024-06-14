package com.loschiferos.ztech.loan.interfaces.rest;

import com.loschiferos.ztech.loan.domain.model.queries.GetAllFlowerpotAssigmentsQuery;
import com.loschiferos.ztech.loan.domain.model.queries.GetFlowerpotAssigmentsByIdQuery;
import com.loschiferos.ztech.loan.domain.model.queries.GetFlowerpotIdsByPlantOwnerIdQuery;
import com.loschiferos.ztech.loan.domain.model.valueobjects.PlantOwnerId;
import com.loschiferos.ztech.loan.domain.services.FlowerpotAssigmentCommandService;
import com.loschiferos.ztech.loan.domain.services.FlowerpotAssigmentQueryService;
import com.loschiferos.ztech.loan.interfaces.rest.resources.CreateFlowerpotAssigmentResource;
import com.loschiferos.ztech.loan.interfaces.rest.resources.FlowerpotAssigmentResource;
import com.loschiferos.ztech.loan.interfaces.rest.transform.CreateFlowerpotAssigmentCommandFromResourceAssembler;
import com.loschiferos.ztech.loan.interfaces.rest.transform.FlowerpotAssigmentResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin(origins = "**" , maxAge = 3600)
@RestController
@RequestMapping(value = "/api/v1/flowerpot/assignments", produces = APPLICATION_JSON_VALUE)
@Tag(name = "flowerpot/assignments", description = "Flowerpot Assignments Management Endpoints")
public class FlowerpotAssignmentsController {
    private final FlowerpotAssigmentCommandService flowerpotAssigmentCommandService;
    private final FlowerpotAssigmentQueryService flowerpotAssigmentQueryService;

    public FlowerpotAssignmentsController(FlowerpotAssigmentCommandService flowerpotAssigmentCommandService, FlowerpotAssigmentQueryService flowerpotAssigmentQueryService) {
        this.flowerpotAssigmentQueryService = flowerpotAssigmentQueryService;
        this.flowerpotAssigmentCommandService = flowerpotAssigmentCommandService;
    }

    @PostMapping
    public ResponseEntity<FlowerpotAssigmentResource> createFlowerpotAssigment(@RequestBody CreateFlowerpotAssigmentResource resource) {
        var createFlowerpotAssigmentCommand = CreateFlowerpotAssigmentCommandFromResourceAssembler.toCommandFromResource(resource);
        var flowerpotAssigmentId = flowerpotAssigmentCommandService.handle(createFlowerpotAssigmentCommand);
        if (flowerpotAssigmentId == 0L) {
            return ResponseEntity.badRequest().build();
        }
        var getFlowerpotAssigmentByIdQuery = new GetFlowerpotAssigmentsByIdQuery(flowerpotAssigmentId);
        var flowerpotAssigment = flowerpotAssigmentQueryService.handle(getFlowerpotAssigmentByIdQuery);
        if (flowerpotAssigment.isEmpty()) return ResponseEntity.badRequest().build();
        var flowerpotAssigmentResource = FlowerpotAssigmentResourceFromEntityAssembler.toResourceFromEntity(flowerpotAssigment.get());
        return new ResponseEntity<>(flowerpotAssigmentResource, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<FlowerpotAssigmentResource>> getAllFlowerpotAssigment() {
        var getAllFlowerpotAssigmentsQuery = new GetAllFlowerpotAssigmentsQuery();
        var flowerpotAssigment = flowerpotAssigmentQueryService.handle(getAllFlowerpotAssigmentsQuery);
        var flowerpotAssigmentResources = flowerpotAssigment.stream().map(FlowerpotAssigmentResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(flowerpotAssigmentResources);
    }


    @GetMapping("/{flowerpotAssigmentId}")
    public ResponseEntity<FlowerpotAssigmentResource> getFlowerpotAssigmentById(@PathVariable Long flowerpotAssigmentId) {
        var getFlowerpotAssigmentByIdQuery = new GetFlowerpotAssigmentsByIdQuery(flowerpotAssigmentId);
        var flowerpotAssigment = flowerpotAssigmentQueryService.handle(getFlowerpotAssigmentByIdQuery);
        if (flowerpotAssigment.isEmpty()) return ResponseEntity.notFound().build();
        var flowerpotAssigmentResource = FlowerpotAssigmentResourceFromEntityAssembler.toResourceFromEntity(flowerpotAssigment.get());
        return ResponseEntity.ok(flowerpotAssigmentResource);
    }

    @GetMapping("/plant/owner/{plantOwnerId}")
    public ResponseEntity<List<Long>> getFlowerpotIdsByPlantOwnerId(@PathVariable Long plantOwnerId) {
        PlantOwnerId plantOwnerIdToRequest = new PlantOwnerId(plantOwnerId);
        var getFlowerpotIdsByPlantOwnerIdQuery = new GetFlowerpotIdsByPlantOwnerIdQuery(plantOwnerIdToRequest);
        var flowerpotIds = flowerpotAssigmentQueryService.handle(getFlowerpotIdsByPlantOwnerIdQuery);
        return ResponseEntity.ok(flowerpotIds);
    }
}
