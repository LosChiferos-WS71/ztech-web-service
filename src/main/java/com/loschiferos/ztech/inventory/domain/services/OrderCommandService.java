package com.loschiferos.ztech.inventory.domain.services;

import com.loschiferos.ztech.inventory.domain.model.commands.AddSupplyToOrderCommand;
import com.loschiferos.ztech.inventory.domain.model.commands.CreateOrderCommand;
import com.loschiferos.ztech.inventory.domain.model.commands.DeleteOrderCommand;
import com.loschiferos.ztech.inventory.domain.model.commands.UpdateOrderCommand;

public interface OrderCommandService {
    Long handle(CreateOrderCommand command);
    void handle(AddSupplyToOrderCommand command);

    void updateOrder(UpdateOrderCommand command);

    void deleteOrder(DeleteOrderCommand command);
}
