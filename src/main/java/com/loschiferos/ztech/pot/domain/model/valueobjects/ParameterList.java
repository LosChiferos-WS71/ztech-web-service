package com.loschiferos.ztech.pot.domain.model.valueobjects;

import com.loschiferos.ztech.pot.domain.model.entities.Parameter;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Embeddable;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Embeddable
public class ParameterList {
    @OneToMany(mappedBy = "plantType", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Parameter> parameters;

    public ParameterList() {
        this.parameters = new ArrayList<>();
    }

    public void createParameter(Parameter parameter) {
        parameters.add(parameter);
    }

    public List<Parameter> getAllParameters() {
        return parameters;
    }

    public List<ParameterType> getAllParameterTypes() {
        return parameters.stream().map(Parameter::getParameterType).collect(Collectors.toList());
    }
}
