package com.loschiferos.ztech.inventory.domain.services;

import com.loschiferos.ztech.inventory.domain.model.aggregates.Supply;

import java.util.List;

public interface SupplyQueryService {

    Supply getSupplyById(Long id);
    List<Supply> getAllSupplies();
}
