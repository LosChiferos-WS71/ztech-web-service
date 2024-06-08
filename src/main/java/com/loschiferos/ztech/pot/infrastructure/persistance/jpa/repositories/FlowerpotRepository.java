package com.loschiferos.ztech.pot.infrastructure.persistance.jpa.repositories;

import com.loschiferos.ztech.pot.domain.model.aggregates.Flowerpot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FlowerpotRepository extends JpaRepository<Flowerpot, Long> {
    boolean existsByCode(String code);
    Optional<Flowerpot> findByCode(String code);
}
