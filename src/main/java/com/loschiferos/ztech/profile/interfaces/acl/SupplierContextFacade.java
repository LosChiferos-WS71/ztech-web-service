package com.loschiferos.ztech.profile.interfaces.acl;

import com.loschiferos.ztech.profile.domain.model.commands.CreateSupplierCommand;
import com.loschiferos.ztech.profile.domain.services.PlantOwnerCommandService;
import com.loschiferos.ztech.profile.domain.services.PlantOwnerQueryService;
import com.loschiferos.ztech.profile.domain.services.SupplierCommandService;
import org.springframework.stereotype.Service;

@Service
public class SupplierContextFacade {
    private final SupplierCommandService supplierCommandService;
    public SupplierContextFacade(SupplierCommandService supplierCommandService) {
        this.supplierCommandService = supplierCommandService;
    }

    public Long saveSupplier(String name, String email, String address, Long phone, String photo, String ruc) {
        var createSupplierCommand = new CreateSupplierCommand(name, email, address, phone, photo, ruc);
        var supplierId = supplierCommandService.handle(createSupplierCommand);
        if(supplierId == 0) return 0L;
        return supplierId;

    }
}
