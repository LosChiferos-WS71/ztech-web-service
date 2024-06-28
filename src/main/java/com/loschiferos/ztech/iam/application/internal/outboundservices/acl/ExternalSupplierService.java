package com.loschiferos.ztech.iam.application.internal.outboundservices.acl;

import com.loschiferos.ztech.iam.domain.model.valueobjects.SupplierId;
import com.loschiferos.ztech.profile.interfaces.acl.SupplierContextFacade;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("externalSupplierServiceIam")
public class ExternalSupplierService {
    private final SupplierContextFacade supplierContextFacade;
    public ExternalSupplierService(SupplierContextFacade supplierContextFacade) {
        this.supplierContextFacade = supplierContextFacade;
    }

    public Optional<SupplierId> saveSupplier(String name, String email, String address, Long phone, String photo, String ruc) {
        var savedSupplierId = supplierContextFacade.saveSupplier(name, email, address, phone, photo, ruc);
        if (savedSupplierId == 0) return Optional.empty();
        return Optional.of(new SupplierId(savedSupplierId));
    }
}
