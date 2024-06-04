package com.loschiferos.ztech.profile.application.internal.queryservices;

import com.loschiferos.ztech.profile.domain.model.aggregates.Supplier;
import com.loschiferos.ztech.profile.domain.model.queries.GetAllSuppliersQuery;
import com.loschiferos.ztech.profile.domain.model.queries.GetSupplierByIdQuery;
import com.loschiferos.ztech.profile.domain.services.SupplierQueryService;
import com.loschiferos.ztech.profile.infrastructure.persistence.jpa.repositories.SupplierRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierQueryServiceImpl implements SupplierQueryService {
    private final SupplierRepository supplierRepository;

    public SupplierQueryServiceImpl(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    @Override
    public Optional<Supplier> handle(GetSupplierByIdQuery query) {
        return supplierRepository.findById(query.id());
    }

    @Override
    public List<Supplier> handle(GetAllSuppliersQuery query) {
        return supplierRepository.findAll();
    }
}
