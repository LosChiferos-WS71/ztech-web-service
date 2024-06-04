package com.loschiferos.ztech.profile.infrastructure.persistence.jpa.repositories;

import com.loschiferos.ztech.profile.domain.model.aggregates.PlantOwner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlantOwnerRepository extends JpaRepository<PlantOwner, Long> {
    boolean existsByDni(int dni);
}
