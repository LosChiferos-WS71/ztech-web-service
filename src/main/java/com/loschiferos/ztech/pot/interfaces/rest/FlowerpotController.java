package com.loschiferos.ztech.pot.interfaces.rest;

import com.loschiferos.ztech.pot.domain.model.commands.CreateFlowerpotCommand;
import com.loschiferos.ztech.pot.domain.services.FlowerpotCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/flowerpots")
public class FlowerpotController {

    private final FlowerpotCommandService flowerpotCommandService;

    @Autowired
    public FlowerpotController(FlowerpotCommandService flowerpotCommandService) {
        this.flowerpotCommandService = flowerpotCommandService;
    }

    @PostMapping
    public ResponseEntity<Long> createFlowerpot(@RequestBody CreateFlowerpotCommand command) {
        Long flowerpotId = flowerpotCommandService.handle(command);
        return ResponseEntity.ok(flowerpotId);
    }
}
