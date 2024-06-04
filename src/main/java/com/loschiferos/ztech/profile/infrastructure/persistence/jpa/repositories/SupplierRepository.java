package com.loschiferos.ztech.profile.infrastructure.persistence.jpa.repositories;

import com.loschiferos.ztech.profile.domain.model.aggregates.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {
    boolean existsByRuc(String ruc);
}
