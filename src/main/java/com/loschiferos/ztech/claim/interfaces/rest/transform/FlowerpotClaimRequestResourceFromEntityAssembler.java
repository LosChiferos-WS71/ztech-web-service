package com.loschiferos.ztech.claim.interfaces.rest.transform;

import com.loschiferos.ztech.claim.domain.model.aggregates.FlowerpotClaimRequest;
import com.loschiferos.ztech.claim.interfaces.rest.resources.FlowerpotClaimRequestResource;

public class FlowerpotClaimRequestResourceFromEntityAssembler {
    public static FlowerpotClaimRequestResource toResourceFromEntity(FlowerpotClaimRequest entity) {
        return new FlowerpotClaimRequestResource(entity.getId(), entity.getMessageBody().getSubject(),
                entity.getMessageBody().getDescription());
    }
}
