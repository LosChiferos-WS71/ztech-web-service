package com.loschiferos.ztech.loan.aplication.internal.outboundedservices.acl;

import com.loschiferos.ztech.loan.domain.model.valueobjects.FlowerpotId;
import com.loschiferos.ztech.pot.interfaces.acl.FlowerpotContextFacade;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExternalFlowerpotService {
    private final FlowerpotContextFacade flowerpotContextFacade;

    public ExternalFlowerpotService(FlowerpotContextFacade flowerpotContextFacade) {
        this.flowerpotContextFacade = flowerpotContextFacade;
    }

    public Optional<FlowerpotId> fetchFlowerpotIdById(Long Id) {
        var flowerpotId = flowerpotContextFacade.getFlowerpotIdById(Id);
        if (flowerpotId == 0L) return Optional.empty();
        return Optional.of(new FlowerpotId(flowerpotId));
    }
}
