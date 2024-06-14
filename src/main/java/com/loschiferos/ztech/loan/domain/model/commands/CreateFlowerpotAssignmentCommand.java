package com.loschiferos.ztech.loan.domain.model.commands;

public record CreateFlowerpotAssignmentCommand(Long plantOwnerId, Long flowerpotId, Long plantTypeId, String name, String photo, String startDate, String endDate) {
}
