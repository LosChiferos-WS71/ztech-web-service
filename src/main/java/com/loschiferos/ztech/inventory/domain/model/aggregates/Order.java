package com.loschiferos.ztech.inventory.domain.model.aggregates;

import com.loschiferos.ztech.inventory.domain.model.entities.OrderSupply;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.logging.log4j.util.Strings;

import java.util.HashSet;
import java.util.Set;

@Getter
@Entity
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer suppliersId;

    @Column(length = 20, nullable = false)
    private String subject;

    @Column(length = 20)
    private String description;

    @Column(length = 10)
    private String state;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<OrderSupply> orderSupplies;

    // Constructor por defecto que inicializa valores a valores por defecto
    public Order() {
        this.subject = Strings.EMPTY;
        this.description = Strings.EMPTY;
        this.state = Strings.EMPTY;
        this.orderSupplies = new HashSet<>();
    }

    // Constructor con parámetros para facilitar la creación
    public Order(Integer suppliersId, String subject, String description, String state) {
        this();
        this.suppliersId = suppliersId;
        this.subject = subject;
        this.description = description;
        this.state = state;
    }

    // Métodos para gestionar orderSupplies
    public void addSupply(OrderSupply orderSupply) {
        orderSupplies.add(orderSupply);
        orderSupply.setOrder(this);
    }

    public void removeSupply(OrderSupply orderSupply) {
        if (orderSupplies.contains(orderSupply)) {
            orderSupplies.remove(orderSupply);
            orderSupply.setOrder(null);
        }
    }

    public void updateOrder(Integer suppliersId, String subject, String description, String state) {
        this.suppliersId = suppliersId;
        this.subject = subject;
        this.description = description;
        this.state = state;
    }


}
