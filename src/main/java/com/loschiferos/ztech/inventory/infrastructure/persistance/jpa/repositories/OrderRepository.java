package com.loschiferos.ztech.inventory.infrastructure.persistance.jpa.repositories;

import com.loschiferos.ztech.inventory.domain.model.aggregates.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}