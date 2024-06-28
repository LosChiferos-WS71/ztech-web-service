package com.loschiferos.ztech.pot.interfaces.rest.resources;

import com.loschiferos.ztech.pot.domain.model.valueobjects.ParameterType;

public record ParameterResource(ParameterType parameterType, Long value) {
}
