package com.loschiferos.ztech.inventory.domain.model.commands;

public record UpdateOrderCommand(Long id, Integer suppliersId, String subject, String description, String state) {
}