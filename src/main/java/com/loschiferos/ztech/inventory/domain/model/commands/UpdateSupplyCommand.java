package com.loschiferos.ztech.inventory.domain.model.commands;

public record UpdateSupplyCommand(Long supplyId, String name, Integer quantity) {
}
