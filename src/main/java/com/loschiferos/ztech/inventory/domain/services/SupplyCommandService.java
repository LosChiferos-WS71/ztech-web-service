package com.loschiferos.ztech.inventory.domain.services;

import com.loschiferos.ztech.inventory.domain.model.commands.CreateSupplyCommand;
import com.loschiferos.ztech.inventory.domain.model.commands.DeleteSupplyCommand;
import com.loschiferos.ztech.inventory.domain.model.commands.UpdateSupplyCommand;

public interface SupplyCommandService {
    Long createSupply(CreateSupplyCommand command);
    void updateSupply(UpdateSupplyCommand command);
    void deleteSupply(DeleteSupplyCommand command);
}
