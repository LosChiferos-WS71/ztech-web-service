package com.loschiferos.ztech.profile.domain.services;

import com.loschiferos.ztech.profile.domain.model.aggregates.Supplier;
import com.loschiferos.ztech.profile.domain.model.queries.GetAllSuppliersQuery;
import com.loschiferos.ztech.profile.domain.model.queries.GetSupplierByIdQuery;

import java.util.List;
import java.util.Optional;

public interface SupplierQueryService {
    Optional<Supplier> handle(GetSupplierByIdQuery query);
    List<Supplier> handle(GetAllSuppliersQuery query);
}
