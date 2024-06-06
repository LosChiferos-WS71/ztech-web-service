package com.loschiferos.ztech.pot.interfaces.acl;

import com.loschiferos.ztech.pot.domain.model.queries.GetFlowerpotByIdQuery;
import com.loschiferos.ztech.pot.domain.services.FlowerpotCommandService;
import com.loschiferos.ztech.pot.domain.services.FlowerpotQueryService;
import org.springframework.stereotype.Service;

@Service
public class FlowerpotContextFacade {
    private final FlowerpotCommandService flowerpotCommandService;
    private final FlowerpotQueryService flowerpotQueryService;

    public FlowerpotContextFacade(FlowerpotCommandService flowerpotCommandService, FlowerpotQueryService flowerpotQueryService) {
        this.flowerpotCommandService = flowerpotCommandService;
        this.flowerpotQueryService = flowerpotQueryService;
    }

    public Long getFlowerpotIdById(Long Id) {
        var getFlowerpotByIdQuery = new GetFlowerpotByIdQuery(Id);
        var flowerpot = flowerpotQueryService.handle(getFlowerpotByIdQuery);
        if (flowerpot.isEmpty()) return 0L;
        return flowerpot.get().getId();
    }
}
