package com.loschiferos.ztech.profile.interfaces.rest;

import com.loschiferos.ztech.profile.domain.model.queries.GetAllSuppliersQuery;
import com.loschiferos.ztech.profile.domain.model.queries.GetSupplierByIdQuery;
import com.loschiferos.ztech.profile.domain.services.SupplierCommandService;
import com.loschiferos.ztech.profile.domain.services.SupplierQueryService;
import com.loschiferos.ztech.profile.interfaces.rest.resources.CreateSupplierResource;
import com.loschiferos.ztech.profile.interfaces.rest.resources.SupplierResource;
import com.loschiferos.ztech.profile.interfaces.rest.transform.CreateSupplierCommandFromResourceAssembler;
import com.loschiferos.ztech.profile.interfaces.rest.transform.SupplierResourceFromEntityAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/suppliers")
public class SupplierController {
    private final SupplierCommandService supplierCommandService;
    private final SupplierQueryService supplierQueryService;

    @Autowired
    public SupplierController(SupplierCommandService supplierCommandService, SupplierQueryService supplierQueryService) {
        this.supplierCommandService = supplierCommandService;
        this.supplierQueryService = supplierQueryService;
    }

    @PostMapping
    public ResponseEntity<SupplierResource> createSupplier(@RequestBody CreateSupplierResource resource) {
        var createSupplierCommand = CreateSupplierCommandFromResourceAssembler.toCommandFromResource(resource);
        var supplierId = supplierCommandService.handle(createSupplierCommand);
        if (supplierId == 0L) {
            return ResponseEntity.badRequest().build();
        }
        var getSupplierByIdQuery = new GetSupplierByIdQuery(supplierId);
        var supplier = supplierQueryService.handle(getSupplierByIdQuery);

        if (supplier.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        var supplierResource = SupplierResourceFromEntityAssembler.toResourceFromEntity(supplier.get());
        return new ResponseEntity<>(supplierResource, HttpStatus.CREATED);
    }

    @GetMapping("/{supplierId}")
    public ResponseEntity<SupplierResource> getSupplierById(@PathVariable Long supplierId) {
        var getSupplierByIdQuery = new GetSupplierByIdQuery(supplierId);
        var supplier = supplierQueryService.handle(getSupplierByIdQuery);
        if (supplier.isEmpty()) return ResponseEntity.badRequest().build();
        var supplierResource = SupplierResourceFromEntityAssembler.toResourceFromEntity(supplier.get());
        return ResponseEntity.ok(supplierResource);
    }

    @GetMapping
    public ResponseEntity<List<SupplierResource>> getAllSuppliers() {
        var getAllSuppliersQuery = new GetAllSuppliersQuery();
        var suppliers = supplierQueryService.handle(getAllSuppliersQuery);
        var supplierResources = suppliers.stream()
                .map(SupplierResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(supplierResources);
    }

    @PutMapping
    public ResponseEntity<SupplierResource> updateSupplier(@RequestBody CreateSupplierResource resource) {
        var createSupplierCommand = CreateSupplierCommandFromResourceAssembler.toCommandFromResource(resource);
        var supplierId = supplierCommandService.handle(createSupplierCommand);
        if (supplierId == 0L) {
            return ResponseEntity.badRequest().build();
        }
        var getSupplierByIdQuery = new GetSupplierByIdQuery(supplierId);
        var supplier = supplierQueryService.handle(getSupplierByIdQuery);

        if (supplier.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        var supplierResource = SupplierResourceFromEntityAssembler.toResourceFromEntity(supplier.get());
        return new ResponseEntity<>(supplierResource, HttpStatus.CREATED);
    }
}
