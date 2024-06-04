package com.loschiferos.ztech.pot.domain.services;

import com.loschiferos.ztech.pot.domain.model.commands.CreateFlowerpotCommand;
import com.loschiferos.ztech.pot.domain.model.commands.CreateSensorCommand;

public interface FlowerpotCommandService {
    Long handle(CreateFlowerpotCommand command);
    void handle(CreateSensorCommand command);
}