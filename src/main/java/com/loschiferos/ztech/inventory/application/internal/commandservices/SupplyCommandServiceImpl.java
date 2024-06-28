package com.loschiferos.ztech.inventory.application.internal.commandservices;

import com.loschiferos.ztech.inventory.domain.model.aggregates.Supply;
import com.loschiferos.ztech.inventory.domain.model.commands.CreateSupplyCommand;
import com.loschiferos.ztech.inventory.domain.model.commands.DeleteSupplyCommand;
import com.loschiferos.ztech.inventory.domain.model.commands.UpdateSupplyCommand;
import com.loschiferos.ztech.inventory.domain.services.SupplyCommandService;
import com.loschiferos.ztech.inventory.infrastructure.persistance.jpa.repositories.SupplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SupplyCommandServiceImpl implements SupplyCommandService {
    private final SupplyRepository supplyRepository;

    @Autowired
    public SupplyCommandServiceImpl(SupplyRepository supplyRepository) {
        this.supplyRepository = supplyRepository;
    }

    @Override
    @Transactional
    public Long createSupply(CreateSupplyCommand command) {
        Supply supply = new Supply(command.name(), command.quantity());
        supply = supplyRepository.save(supply);
        return supply.getId();
    }

    @Override
    @Transactional
    public void updateSupply(UpdateSupplyCommand command) {
        Supply supply = supplyRepository.findById(command.supplyId())
                .orElseThrow(() -> new RuntimeException("Supply not found"));
        // Utiliza el método update de la entidad Supply para actualizar el nombre y la cantidad
        supply.update(command.name(), command.quantity());
        supplyRepository.save(supply); // Guarda los cambios en la base de datos
    }

    @Override
    @Transactional
    public void deleteSupply(DeleteSupplyCommand command) {
        supplyRepository.deleteById(command.supplyId());
    }
}