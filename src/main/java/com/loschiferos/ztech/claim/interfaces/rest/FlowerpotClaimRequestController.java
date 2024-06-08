package com.loschiferos.ztech.claim.interfaces.rest;

import com.loschiferos.ztech.claim.domain.model.queries.GetFlowerpotClaimRequestByIdQuery;
import com.loschiferos.ztech.claim.domain.services.FlowerpotClaimRequestCommandService;
import com.loschiferos.ztech.claim.domain.services.FlowerpotClaimRequestQueryService;
import com.loschiferos.ztech.claim.interfaces.rest.resources.CreateFlowerpotClaimRequestResource;
import com.loschiferos.ztech.claim.interfaces.rest.resources.FlowerpotClaimRequestResource;
import com.loschiferos.ztech.claim.interfaces.rest.transform.CreateFlowerpotClaimRequestCommandFromResourceAssembler;
import com.loschiferos.ztech.claim.interfaces.rest.transform.FlowerpotClaimRequestResourceFromEntityAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/claims")
public class FlowerpotClaimRequestController {

    private final FlowerpotClaimRequestCommandService flowerpotClaimRequestCommandService;
    private final FlowerpotClaimRequestQueryService flowerpotClaimRequestQueryService;

    @Autowired
    public FlowerpotClaimRequestController(FlowerpotClaimRequestCommandService flowerpotClaimRequestCommandService,
                                           FlowerpotClaimRequestQueryService flowerpotClaimRequestQueryService) {
        this.flowerpotClaimRequestCommandService = flowerpotClaimRequestCommandService;
        this.flowerpotClaimRequestQueryService = flowerpotClaimRequestQueryService;
    }

    @PostMapping
    public ResponseEntity<FlowerpotClaimRequestResource> createFlowerpotClaimRequest(@RequestBody CreateFlowerpotClaimRequestResource resource) {
        var createFlowerpotClaimRequestCommand = CreateFlowerpotClaimRequestCommandFromResourceAssembler.toCommandFromResource(resource);
        var flowerpotClaimRequestId = flowerpotClaimRequestCommandService.handle(createFlowerpotClaimRequestCommand);
        if(flowerpotClaimRequestId == 0L) {
            return ResponseEntity.badRequest().build();
        }
        var getFlowerpotClaimRequestByIdQuery = new GetFlowerpotClaimRequestByIdQuery(flowerpotClaimRequestId);
        var flowerpotClaimRequest = flowerpotClaimRequestQueryService.handle(getFlowerpotClaimRequestByIdQuery);
        if(flowerpotClaimRequest.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var flowerpotClaimRequestResource = FlowerpotClaimRequestResourceFromEntityAssembler.toResourceFromEntity(flowerpotClaimRequest.get());
        return new ResponseEntity<>(flowerpotClaimRequestResource, HttpStatus.CREATED);
    }

    @GetMapping("/{flowerpotClaimRequestId}")
    public ResponseEntity<FlowerpotClaimRequestResource> getFlowerpotClaimRequestById(@PathVariable Long flowerpotClaimRequestId) {
        var getFlowerpotClaimRequestByIdQuery = new GetFlowerpotClaimRequestByIdQuery(flowerpotClaimRequestId);
        var flowerpotClaimRequest = flowerpotClaimRequestQueryService.handle(getFlowerpotClaimRequestByIdQuery);
        if(flowerpotClaimRequest.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var flowerpotClaimRequestResource = FlowerpotClaimRequestResourceFromEntityAssembler.toResourceFromEntity(flowerpotClaimRequest.get());
        return ResponseEntity.ok(flowerpotClaimRequestResource);
    }
}
