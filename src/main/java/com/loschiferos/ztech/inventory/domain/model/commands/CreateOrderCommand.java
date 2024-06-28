package com.loschiferos.ztech.inventory.domain.model.commands;

public record CreateOrderCommand(Integer suppliersId, String subject, String description, String state) {
}
