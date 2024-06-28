package com.loschiferos.ztech.inventory.application.internal.queryservices;

import com.loschiferos.ztech.inventory.domain.model.aggregates.Order;
import com.loschiferos.ztech.inventory.domain.model.aggregates.Supply;
import com.loschiferos.ztech.inventory.domain.model.entities.OrderSupply;
import com.loschiferos.ztech.inventory.domain.services.OrderQueryService;
import com.loschiferos.ztech.inventory.infrastructure.persistance.jpa.repositories.OrderRepository;
import com.loschiferos.ztech.inventory.infrastructure.persistance.jpa.repositories.SupplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderQueryServiceImpl implements OrderQueryService {
    private final OrderRepository orderRepository;
    private final SupplyRepository supplyRepository;

    @Autowired
    public OrderQueryServiceImpl(OrderRepository orderRepository, SupplyRepository supplyRepository) {
        this.orderRepository = orderRepository;
        this.supplyRepository = supplyRepository;
    }

    @Override
    public Order getOrderById(Long orderId) {
        // Fetch the order and return it, or return null if not found
        return orderRepository.findById(orderId).orElse(null);
    }

    @Override
    public List<Order> getAllOrders() {
        // Fetch all orders and return them
        return orderRepository.findAll();
    }

    @Override
    public List<Supply> getSuppliesByOrderId(Long orderId) {
        // Fetch the order and its supplies
        Optional<Order> order = orderRepository.findById(orderId);
        if (order.isPresent()) {
            // Extract supplies from the order
            return order.get().getOrderSupplies().stream()
                    .map(OrderSupply::getSupply)
                    .collect(Collectors.toList());
        }
        return List.of(); // Return an empty list if no order is found
    }
}