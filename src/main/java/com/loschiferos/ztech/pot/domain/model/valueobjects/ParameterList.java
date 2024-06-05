package com.loschiferos.ztech.pot.domain.model.valueobjects;

import com.loschiferos.ztech.pot.domain.model.entities.Parameter;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Embeddable;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

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
}
