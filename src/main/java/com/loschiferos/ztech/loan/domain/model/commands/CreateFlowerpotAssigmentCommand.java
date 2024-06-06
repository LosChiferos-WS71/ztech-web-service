package com.loschiferos.ztech.loan.domain.model.commands;

public record CreateFlowerpotAssigmentCommand(Long plantOwnerId, Long flowerpotId, Long plantTypeId, String name, String photo, String startDate, String endDate) {
}
