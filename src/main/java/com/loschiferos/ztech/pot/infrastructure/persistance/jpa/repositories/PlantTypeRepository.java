package com.loschiferos.ztech.pot.infrastructure.persistance.jpa.repositories;

import com.loschiferos.ztech.pot.domain.model.aggregates.PlantType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlantTypeRepository extends JpaRepository<PlantType, Long> {
    boolean existsByName(String name);
    Optional<PlantType> findByName(String name);
}
