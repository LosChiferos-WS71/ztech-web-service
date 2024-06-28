package com.loschiferos.ztech.inventory.domain.model.entities;

import com.loschiferos.ztech.inventory.domain.model.aggregates.Order;
import com.loschiferos.ztech.inventory.domain.model.aggregates.Supply;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class OrderSupply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "orders_id", nullable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(name = "supplies_id", nullable = false)
    private Supply supply;

    private Integer quantity; // Campo para manejar la cantidad de suministro en el pedido

    // Constructor que facilita la creación de relaciones
    public OrderSupply(Order order, Supply supply) {
        this.order = order;
        this.supply = supply;
    }
}