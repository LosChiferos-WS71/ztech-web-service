package com.loschiferos.ztech.claim.interfaces.rest.transform;

import com.loschiferos.ztech.claim.domain.model.commands.CreateFlowerpotClaimRequestCommand;
import com.loschiferos.ztech.claim.interfaces.rest.resources.CreateFlowerpotClaimRequestResource;

public class CreateFlowerpotClaimRequestCommandFromResourceAssembler {
    public static CreateFlowerpotClaimRequestCommand toCommandFromResource(CreateFlowerpotClaimRequestResource resource) {
        return new CreateFlowerpotClaimRequestCommand(resource.subject(), resource.description());
    }
}
