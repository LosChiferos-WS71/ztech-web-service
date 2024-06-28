package com.loschiferos.ztech.inventory.interfaces.rest.resources;

public record CreateOrderResource(Integer suppliersId, String subject, String description, String state) {
}