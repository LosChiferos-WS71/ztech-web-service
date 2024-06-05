package com.loschiferos.ztech.loan.domain.model.commands;

public record CreateFlowerpotAssigmentCommand(String name, String photo, String startDate, String endDate, Long flowerpotMetricId) {
}
