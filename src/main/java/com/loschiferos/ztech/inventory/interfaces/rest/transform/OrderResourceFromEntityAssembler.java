package com.loschiferos.ztech.inventory.interfaces.rest.transform;

import com.loschiferos.ztech.inventory.domain.model.aggregates.Order;
import com.loschiferos.ztech.inventory.interfaces.rest.resources.OrderResource;

public class OrderResourceFromEntityAssembler {
    public static OrderResource toResourceFromEntity(Order order) {
        return new OrderResource(order.getId(), order.getSuppliersId(), order.getSubject(), order.getDescription(), order.getState());
    }
}