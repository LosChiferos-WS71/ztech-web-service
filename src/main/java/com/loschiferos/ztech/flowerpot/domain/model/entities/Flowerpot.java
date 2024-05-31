package com.loschiferos.ztech.flowerpot.domain.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
public class Flowerpot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;

    private boolean state;

    public Flowerpot(String code, boolean state) {
        this();
        this.code = code;
        this.state = state;
    }

    public Flowerpot() {
        this.code = "";
        this.state = false;
    }
}
