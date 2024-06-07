package com.loschiferos.ztech.profile.application.internal.commandservices;

import com.loschiferos.ztech.profile.domain.model.aggregates.Supplier;
import com.loschiferos.ztech.profile.domain.model.commands.CreateSupplierCommand;
import com.loschiferos.ztech.profile.domain.model.valueobjects.Profile;
import com.loschiferos.ztech.profile.domain.services.SupplierCommandService;
import com.loschiferos.ztech.profile.infrastructure.persistence.jpa.repositories.SupplierRepository;
import org.springframework.stereotype.Service;

@Service
public class SupplierCommandServiceImpl implements SupplierCommandService {
    private final SupplierRepository supplierRepository;

    public SupplierCommandServiceImpl(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    @Override
    public Long handle(CreateSupplierCommand command) {
        if(supplierRepository.existsByRuc(command.ruc())) {
            throw new IllegalArgumentException("Supplier with same ruc already exists");
        }
        Profile profile = new Profile(command.name(), command.email(), command.address(), command.phone(), command.photo());
        Supplier supplier = new Supplier(profile, command.ruc());
        Supplier savedSupplier = supplierRepository.save(supplier);
        return savedSupplier.getId();
    }
}
