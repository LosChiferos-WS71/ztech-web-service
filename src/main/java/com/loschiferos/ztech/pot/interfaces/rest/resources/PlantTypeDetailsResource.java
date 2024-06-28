package com.loschiferos.ztech.pot.interfaces.rest.resources;

import java.util.List;

public record PlantTypeDetailsResource(PlantTypeResource plantType, List<ParameterResource> parameters) {
}
