package com.loschiferos.ztech.inventory.domain.model.aggregates;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor  // Constructor por defecto generado por Lombok
public class Supply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer quantity;

    public Supply(String name, Integer quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public void update(String name, Integer quantity) {
        this.name = name;
        this.quantity = quantity;
    }
}