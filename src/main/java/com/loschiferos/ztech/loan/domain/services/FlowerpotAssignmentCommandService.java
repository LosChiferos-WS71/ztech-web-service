package com.loschiferos.ztech.loan.domain.services;


import com.loschiferos.ztech.loan.domain.model.commands.CreateFlowerpotAssignmentCommand;
import com.loschiferos.ztech.loan.domain.model.commands.RequestFlowerpotAssignmentCommand;

public interface FlowerpotAssignmentCommandService {
    Long handle(CreateFlowerpotAssignmentCommand command);
    Long handle(RequestFlowerpotAssignmentCommand command);
}
