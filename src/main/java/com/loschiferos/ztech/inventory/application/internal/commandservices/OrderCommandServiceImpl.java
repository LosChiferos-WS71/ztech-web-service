package com.loschiferos.ztech.inventory.application.internal.commandservices;

import com.loschiferos.ztech.inventory.domain.model.aggregates.Order;
import com.loschiferos.ztech.inventory.domain.model.aggregates.Supply;
import com.loschiferos.ztech.inventory.domain.model.commands.AddSupplyToOrderCommand;
import com.loschiferos.ztech.inventory.domain.model.commands.CreateOrderCommand;
import com.loschiferos.ztech.inventory.domain.model.commands.DeleteOrderCommand;
import com.loschiferos.ztech.inventory.domain.model.commands.UpdateOrderCommand;
import com.loschiferos.ztech.inventory.domain.model.entities.OrderSupply;
import com.loschiferos.ztech.inventory.domain.services.OrderCommandService;
import com.loschiferos.ztech.inventory.infrastructure.persistance.jpa.repositories.OrderRepository;
import com.loschiferos.ztech.inventory.infrastructure.persistance.jpa.repositories.SupplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderCommandServiceImpl implements OrderCommandService {
    private final OrderRepository orderRepository;
    private final SupplyRepository supplyRepository;

    @Autowired
    public OrderCommandServiceImpl(OrderRepository orderRepository, SupplyRepository supplyRepository) {
        this.orderRepository = orderRepository;
        this.supplyRepository = supplyRepository;
    }

    @Override
    @Transactional
    public Long handle(CreateOrderCommand command) {
        Order order = new Order(command.suppliersId(), command.subject(), command.description(), command.state());
        order = orderRepository.save(order);
        return order.getId();
    }

    @Override
    @Transactional
    public void handle(AddSupplyToOrderCommand command) {
        Order order = orderRepository.findById(command.orderId())
                .orElseThrow(() -> new IllegalArgumentException("Order not found with ID: " + command.orderId()));
        Supply supply = supplyRepository.findById(command.supplyId())
                .orElseThrow(() -> new IllegalArgumentException("Supply not found with ID: " + command.supplyId()));

        OrderSupply orderSupply = new OrderSupply(order, supply);
        orderSupply.setQuantity(command.quantity());  // This requires adding a setQuantity method in OrderSupply
        order.addSupply(orderSupply);

        orderRepository.save(order);
    }

    @Transactional
    public void updateOrder(UpdateOrderCommand command) {
        Order order = orderRepository.findById(command.id())
                .orElseThrow(() -> new RuntimeException("Order not found"));
        order.updateOrder(command.suppliersId(), command.subject(), command.description(), command.state());
        orderRepository.save(order);
    }

    @Override
    @Transactional
    public void deleteOrder(DeleteOrderCommand command) {
        Order order = orderRepository.findById(command.orderId())
                .orElseThrow(() -> new RuntimeException("Order not found"));
        orderRepository.delete(order);
    }
}