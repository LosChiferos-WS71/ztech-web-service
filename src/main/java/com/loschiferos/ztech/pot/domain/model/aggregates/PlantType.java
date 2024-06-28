package com.loschiferos.ztech.pot.domain.model.aggregates;

import com.loschiferos.ztech.pot.domain.model.entities.Parameter;
import com.loschiferos.ztech.pot.domain.model.valueobjects.ParameterList;
import com.loschiferos.ztech.pot.domain.model.valueobjects.ParameterType;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.logging.log4j.util.Strings;

import java.util.List;

@Getter
@Entity
@AllArgsConstructor
public class PlantType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String scientificName;

    private String photo;

    private String description;

    @Embedded
    private final ParameterList parameterList;

    public PlantType() {
        this.name = Strings.EMPTY;
        this.scientificName = Strings.EMPTY;
        this.photo = Strings.EMPTY;
        this.description = Strings.EMPTY;
        this.parameterList = new ParameterList();
    }

    public PlantType(String name, String scientificName, String photo, String description) {
        this();
        this.name = name;
        this.scientificName = scientificName;
        this.photo = photo;
        this.description = description;
    }

    public void createParameter(ParameterType type, Long value) {
        Parameter parameter = new Parameter(this, type, value);
        this.parameterList.createParameter(parameter);
    }

    public List<Parameter> getAllParameters() {
        return parameterList.getAllParameters();
    }
}
