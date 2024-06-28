package com.loschiferos.ztech.inventory.domain.model.commands;

public record AddSupplyToOrderCommand(Long orderId, Long supplyId, Integer quantity) {
}
