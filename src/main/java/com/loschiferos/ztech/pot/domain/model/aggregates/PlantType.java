package com.loschiferos.ztech.pot.domain.model.aggregates;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.logging.log4j.util.Strings;

@Getter
@Entity
@AllArgsConstructor
public class PlantType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    public PlantType() {
        this.name = Strings.EMPTY;
        this.description = Strings.EMPTY;
    }

    public PlantType(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
