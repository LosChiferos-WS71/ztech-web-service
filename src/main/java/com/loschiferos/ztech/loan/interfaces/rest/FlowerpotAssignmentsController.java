package com.loschiferos.ztech.loan.interfaces.rest;

import com.loschiferos.ztech.loan.domain.model.queries.GetAllFlowerpotAssigmentsQuery;
import com.loschiferos.ztech.loan.domain.model.queries.GetFlowerpotAssigmentsByIdQuery;
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

@RestController
@RequestMapping(value = "/api/v1/flowerpot-assignments", produces = APPLICATION_JSON_VALUE)
@Tag(name = "flowerpot-assignments", description = "Flowerpot Assignments Management Endpoints")
public class FlowerpotAssignmentsController {
    private final FlowerpotAssigmentCommandService flowerpotAssigmentCommandService;
    private final FlowerpotAssigmentQueryService flowerpotAssigmentQueryService;

    public FlowerpotAssignmentsController(FlowerpotAssigmentCommandService flowerpotAssigmentCommandService, FlowerpotAssigmentQueryService flowerpotAssigmentQueryService) {
        this.flowerpotAssigmentQueryService = flowerpotAssigmentQueryService;
        this.flowerpotAssigmentCommandService = flowerpotAssigmentCommandService;
    }

    @PostMapping
    public ResponseEntity<FlowerpotAssigmentResource> createFlowerpotAssigmentCommand(@RequestBody CreateFlowerpotAssigmentResource resource) {
        var requestEnrollmentCommand = CreateFlowerpotAssigmentCommandFromResourceAssembler.toCommandFromResource(resource);
        var enrollmentId = flowerpotAssigmentCommandService.handle(requestEnrollmentCommand);
        if (enrollmentId == 0L) return ResponseEntity.badRequest().build();

        var getEnrollmentByIdQuery = new GetFlowerpotAssigmentsByIdQuery(enrollmentId);
        var enrollment = flowerpotAssigmentQueryService.handle(getEnrollmentByIdQuery);
        if (enrollment.isEmpty()) return ResponseEntity.notFound().build();
        var enrollmentResource = FlowerpotAssigmentResourceFromEntityAssembler.toResourceFromEntity(enrollment.get());
        return new ResponseEntity<>(enrollmentResource, HttpStatus.CREATED);

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
}
