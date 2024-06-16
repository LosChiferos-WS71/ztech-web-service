package com.loschiferos.ztech.loan.interfaces.rest;

import com.loschiferos.ztech.loan.domain.model.queries.*;
import com.loschiferos.ztech.loan.domain.model.valueobjects.FlowerpotId;
import com.loschiferos.ztech.loan.domain.model.valueobjects.PlantOwnerId;
import com.loschiferos.ztech.loan.domain.services.FlowerpotAssignmentCommandService;
import com.loschiferos.ztech.loan.domain.services.FlowerpotAssignmentQueryService;
import com.loschiferos.ztech.loan.interfaces.rest.resources.CreateFlowerpotAssignmentResource;
import com.loschiferos.ztech.loan.interfaces.rest.resources.FlowerpotAssignmentResource;
import com.loschiferos.ztech.loan.interfaces.rest.transform.CreateFlowerpotAssignmentCommandFromResourceAssembler;
import com.loschiferos.ztech.loan.interfaces.rest.transform.FlowerpotAssignmentResourceFromEntityAssembler;
import com.loschiferos.ztech.shared.domain.exceptions.ResourceNotFoundException;
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
    private final FlowerpotAssignmentCommandService flowerpotAssignmentCommandService;
    private final FlowerpotAssignmentQueryService flowerpotAssignmentQueryService;

    public FlowerpotAssignmentsController(FlowerpotAssignmentCommandService flowerpotAssignmentCommandService, FlowerpotAssignmentQueryService flowerpotAssignmentQueryService) {
        this.flowerpotAssignmentQueryService = flowerpotAssignmentQueryService;
        this.flowerpotAssignmentCommandService = flowerpotAssignmentCommandService;
    }

    @PostMapping
    public ResponseEntity<FlowerpotAssignmentResource> createFlowerpotAssignment(@RequestBody CreateFlowerpotAssignmentResource resource) {
        var createFlowerpotAssignmentCommand = CreateFlowerpotAssignmentCommandFromResourceAssembler.toCommandFromResource(resource);
        var flowerpotAssignmentId = flowerpotAssignmentCommandService.handle(createFlowerpotAssignmentCommand);
        if (flowerpotAssignmentId == 0L) {
            return ResponseEntity.badRequest().build();
        }
        var getFlowerpotAssignmentByIdQuery = new GetFlowerpotAssignmentsByIdQuery(flowerpotAssignmentId);
        var flowerpotAssignment = flowerpotAssignmentQueryService.handle(getFlowerpotAssignmentByIdQuery);
        if (flowerpotAssignment.isEmpty()) return ResponseEntity.badRequest().build();
        var flowerpotAssignmentResource = FlowerpotAssignmentResourceFromEntityAssembler.toResourceFromEntity(flowerpotAssignment.get());
        return new ResponseEntity<>(flowerpotAssignmentResource, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<FlowerpotAssignmentResource>> getAllFlowerpotAssignment() {
        var getAllFlowerpotAssignmentsQuery = new GetAllFlowerpotAssignmentsQuery();
        var flowerpotAssignment = flowerpotAssignmentQueryService.handle(getAllFlowerpotAssignmentsQuery);
        var flowerpotAssignmentResources = flowerpotAssignment.stream().map(FlowerpotAssignmentResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(flowerpotAssignmentResources);
    }


    @GetMapping("/{flowerpotAssignmentId}")
    public ResponseEntity<FlowerpotAssignmentResource> getFlowerpotAssignmentById(@PathVariable Long flowerpotAssignmentId) {
        var getFlowerpotAssignmentByIdQuery = new GetFlowerpotAssignmentsByIdQuery(flowerpotAssignmentId);
        var flowerpotAssignment = flowerpotAssignmentQueryService.handle(getFlowerpotAssignmentByIdQuery);
        if (flowerpotAssignment.isEmpty()) return ResponseEntity.notFound().build();
        var flowerpotAssignmentResource = FlowerpotAssignmentResourceFromEntityAssembler.toResourceFromEntity(flowerpotAssignment.get());
        return ResponseEntity.ok(flowerpotAssignmentResource);
    }

    @GetMapping("/plant/owner/{plantOwnerId}")
    public ResponseEntity<List<Long>> getFlowerpotIdsByPlantOwnerId(@PathVariable Long plantOwnerId) {
        PlantOwnerId plantOwnerIdToRequest = new PlantOwnerId(plantOwnerId);
        var getFlowerpotIdsByPlantOwnerIdQuery = new GetFlowerpotIdsByPlantOwnerIdQuery(plantOwnerIdToRequest);
        var flowerpotIds = flowerpotAssignmentQueryService.handle(getFlowerpotIdsByPlantOwnerIdQuery);
        return ResponseEntity.ok(flowerpotIds);
    }

    @PostMapping("/flowerpotId")
    public ResponseEntity<FlowerpotAssignmentResource> getFlowerpotAssignmentByFlowerpotId(@RequestBody Long flowerpotId) {
        FlowerpotId flowerpotIdToRequest = new FlowerpotId(flowerpotId);
        var getFlowerpotAssignmentByFlowerpotIdQuery = new GetFlowerpotAssignmentByFlowerpotId(flowerpotIdToRequest);
        var flowerpotAssignment = flowerpotAssignmentQueryService.handle(getFlowerpotAssignmentByFlowerpotIdQuery);
        if (flowerpotAssignment.isEmpty()) throw new ResourceNotFoundException("Flowerpot Assignment not found");
        var flowerpotAssignmentResource = FlowerpotAssignmentResourceFromEntityAssembler.toResourceFromEntity(flowerpotAssignment.get());
        return ResponseEntity.ok(flowerpotAssignmentResource);
    }

    @PostMapping("/plant/owner/plantTypeIds")
    public ResponseEntity<List<Long>> getPlantTypeIdsByPlantOwnerId(@RequestBody Long plantOwnerId) {
        PlantOwnerId plantOwnerIdToRequest = new PlantOwnerId(plantOwnerId);
        var getPlantTypeIdsByPlantOwnerIdQuery = new GetPlantTypeIdsByPlantOwnerIdQuery(plantOwnerIdToRequest);
        var plantTypeIds = flowerpotAssignmentQueryService.handle(getPlantTypeIdsByPlantOwnerIdQuery);
        return ResponseEntity.ok(plantTypeIds);
    }
}
