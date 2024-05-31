package com.loschiferos.ztech.flowerpot.domain.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Flowerpot {
    @Id
    @GeneratedValue
    private Long id;

    private String code;

    private boolean state;
}
