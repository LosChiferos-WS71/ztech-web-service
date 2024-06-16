package com.loschiferos.ztech.pot.domain.model.commands;

public record CreatePlantTypeCommand(String name, String scientificName, String photo, String description) {
}
