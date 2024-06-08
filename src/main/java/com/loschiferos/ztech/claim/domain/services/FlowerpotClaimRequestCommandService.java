package com.loschiferos.ztech.claim.domain.services;

import com.loschiferos.ztech.claim.domain.model.commands.CreateFlowerpotClaimRequestCommand;

public interface FlowerpotClaimRequestCommandService {
    Long handle(CreateFlowerpotClaimRequestCommand command);
}