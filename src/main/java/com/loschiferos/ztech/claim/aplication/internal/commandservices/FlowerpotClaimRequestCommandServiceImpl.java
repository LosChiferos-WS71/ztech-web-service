package com.loschiferos.ztech.claim.aplication.internal.commandservices;

import com.loschiferos.ztech.claim.domain.model.aggregates.FlowerpotClaimRequest;
import com.loschiferos.ztech.claim.domain.model.commands.CreateFlowerpotClaimRequestCommand;
import com.loschiferos.ztech.claim.domain.model.valueobjects.MessageBody;
import com.loschiferos.ztech.claim.domain.services.FlowerpotClaimRequestCommandService;
import com.loschiferos.ztech.claim.infraestructure.persistence.jpa.repositories.FlowerpotClaimRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FlowerpotClaimRequestCommandServiceImpl implements FlowerpotClaimRequestCommandService {

    private final FlowerpotClaimRequestRepository flowerpotClaimRequestRepository;

    @Autowired
    public FlowerpotClaimRequestCommandServiceImpl(FlowerpotClaimRequestRepository flowerpotClaimRequestRepository) {
        this.flowerpotClaimRequestRepository = flowerpotClaimRequestRepository;
    }

    @Override
    public Long handle(CreateFlowerpotClaimRequestCommand command) {

        MessageBody messageBody = new MessageBody(command.subject(),command.description());
        FlowerpotClaimRequest flowerpotClaimRequest = new FlowerpotClaimRequest(messageBody);
        FlowerpotClaimRequest savedFlowerpotClaimRequest = flowerpotClaimRequestRepository.save(flowerpotClaimRequest);
        return savedFlowerpotClaimRequest.getId();
    }
}