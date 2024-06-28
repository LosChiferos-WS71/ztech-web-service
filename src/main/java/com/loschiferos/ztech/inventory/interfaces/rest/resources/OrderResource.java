package com.loschiferos.ztech.inventory.interfaces.rest.resources;

public record OrderResource(Long id, Integer suppliersId, String subject, String description, String state) {
}