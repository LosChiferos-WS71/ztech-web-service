package com.loschiferos.ztech.inventory.application.internal.queryservices;

import com.loschiferos.ztech.inventory.domain.model.aggregates.Supply;
import com.loschiferos.ztech.inventory.domain.services.SupplyQueryService;
import com.loschiferos.ztech.inventory.infrastructure.persistance.jpa.repositories.SupplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplyQueryServiceImpl implements SupplyQueryService {

    private final SupplyRepository supplyRepository;

    @Autowired
    public SupplyQueryServiceImpl(SupplyRepository supplyRepository) {
        this.supplyRepository = supplyRepository;
    }

    @Override
    public Supply getSupplyById(Long id) {
        return supplyRepository.findById(id).orElse(null);
    }

    @Override
    public List<Supply> getAllSupplies() {
        return supplyRepository.findAll();
    }
}