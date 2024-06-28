package com.loschiferos.ztech.inventory.interfaces.rest;

import com.loschiferos.ztech.inventory.domain.model.commands.DeleteOrderCommand;
import com.loschiferos.ztech.inventory.domain.services.OrderCommandService;
import com.loschiferos.ztech.inventory.domain.services.OrderQueryService;
import com.loschiferos.ztech.inventory.interfaces.rest.resources.CreateOrderResource;
import com.loschiferos.ztech.inventory.interfaces.rest.resources.OrderResource;
import com.loschiferos.ztech.inventory.interfaces.rest.transform.CreateOrderCommandFromResourceAssembler;
import com.loschiferos.ztech.inventory.interfaces.rest.transform.OrderResourceFromEntityAssembler;
import com.loschiferos.ztech.inventory.interfaces.rest.transform.UpdateOrderCommandFromResourceAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderCommandService orderCommandService;
    private final OrderQueryService orderQueryService;

    @Autowired
    public OrderController(OrderCommandService orderCommandService, OrderQueryService orderQueryService) {
        this.orderCommandService = orderCommandService;
        this.orderQueryService = orderQueryService;
    }

    @PostMapping
    public ResponseEntity<OrderResource> createOrder(@RequestBody CreateOrderResource resource) {
        var command = CreateOrderCommandFromResourceAssembler.toCommandFromResource(resource);
        Long orderId = orderCommandService.handle(command);
        var order = orderQueryService.getOrderById(orderId);  // Using OrderQueryService here
        if (order == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        var orderResource = OrderResourceFromEntityAssembler.toResourceFromEntity(order);
        return new ResponseEntity<>(orderResource, HttpStatus.CREATED);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResource> getOrderById(@PathVariable Long orderId) {
        var order = orderQueryService.getOrderById(orderId);
        if (order == null) {
            return ResponseEntity.notFound().build();
        }
        var orderResource = OrderResourceFromEntityAssembler.toResourceFromEntity(order);
        return ResponseEntity.ok(orderResource);
    }

    @GetMapping
    public ResponseEntity<List<OrderResource>> getAllOrders() {
        // Cambio para usar orderQueryService para operaciones de consulta
        var orders = orderQueryService.getAllOrders(); // Utiliza el servicio de consulta para obtener todos los pedidos
        var orderResources = orders.stream()
                .map(OrderResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(orderResources);
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<OrderResource> updateOrder(@PathVariable Long orderId, @RequestBody CreateOrderResource resource) {
        var command = UpdateOrderCommandFromResourceAssembler.toCommandFromResource(orderId, resource);
        orderCommandService.updateOrder(command);
        var updatedOrder = orderQueryService.getOrderById(orderId);
        if (updatedOrder == null) {
            return ResponseEntity.notFound().build();
        }
        var orderResource = OrderResourceFromEntityAssembler.toResourceFromEntity(updatedOrder);
        return ResponseEntity.ok(orderResource);
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long orderId) {
        try {
            DeleteOrderCommand command = new DeleteOrderCommand(orderId);
            orderCommandService.deleteOrder(command);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
