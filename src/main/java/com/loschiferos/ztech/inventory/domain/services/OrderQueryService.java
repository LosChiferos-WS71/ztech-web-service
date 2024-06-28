package com.loschiferos.ztech.inventory.domain.services;

import com.loschiferos.ztech.inventory.domain.model.aggregates.Order;
import com.loschiferos.ztech.inventory.domain.model.aggregates.Supply;

import java.util.List;

public interface OrderQueryService {
    Order getOrderById(Long orderId);
    List<Order> getAllOrders();
    List<Supply> getSuppliesByOrderId(Long orderId);
}