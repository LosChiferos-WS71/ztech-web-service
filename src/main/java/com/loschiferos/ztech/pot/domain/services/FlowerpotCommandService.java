package com.loschiferos.ztech.pot.domain.services;

import com.loschiferos.ztech.pot.domain.model.commands.CreateFlowerpotCommand;

public interface FlowerpotCommandService {
    Long handle(CreateFlowerpotCommand command);
}