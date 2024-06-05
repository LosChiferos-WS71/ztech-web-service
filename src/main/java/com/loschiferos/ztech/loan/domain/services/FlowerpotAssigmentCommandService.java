package com.loschiferos.ztech.loan.domain.services;


import com.loschiferos.ztech.loan.domain.model.commands.CreateFlowerpotAssigmentCommand;
import com.loschiferos.ztech.loan.domain.model.commands.RequestFlowerpotAssigmentCommand;

public interface FlowerpotAssigmentCommandService {
    Long handle(CreateFlowerpotAssigmentCommand command);

    Long handle(RequestFlowerpotAssigmentCommand command);

}
