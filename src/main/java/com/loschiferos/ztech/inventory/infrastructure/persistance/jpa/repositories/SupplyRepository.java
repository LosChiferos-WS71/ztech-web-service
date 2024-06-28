package com.loschiferos.ztech.inventory.infrastructure.persistance.jpa.repositories;

import com.loschiferos.ztech.inventory.domain.model.aggregates.Supply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplyRepository extends JpaRepository<Supply, Long> {
}