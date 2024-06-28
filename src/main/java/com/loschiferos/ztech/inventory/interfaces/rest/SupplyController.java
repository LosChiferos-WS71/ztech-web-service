package com.loschiferos.ztech.inventory.interfaces.rest;

import com.loschiferos.ztech.inventory.domain.model.aggregates.Supply;
import com.loschiferos.ztech.inventory.domain.model.commands.DeleteSupplyCommand;
import com.loschiferos.ztech.inventory.domain.model.commands.UpdateSupplyCommand;
import com.loschiferos.ztech.inventory.domain.services.SupplyCommandService;
import com.loschiferos.ztech.inventory.domain.services.SupplyQueryService;
import com.loschiferos.ztech.inventory.interfaces.rest.resources.CreateSupplyResource;
import com.loschiferos.ztech.inventory.interfaces.rest.resources.SupplyResource;
import com.loschiferos.ztech.inventory.interfaces.rest.transform.CreateSupplyCommandFromResourceAssembler;
import com.loschiferos.ztech.inventory.interfaces.rest.transform.SupplyResourceFromEntityAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/supplies")
public class SupplyController {

    private final SupplyCommandService supplyCommandService;
    private final SupplyQueryService supplyQueryService;

    @Autowired
    public SupplyController(SupplyCommandService supplyCommandService, SupplyQueryService supplyQueryService) {
        this.supplyCommandService = supplyCommandService;
        this.supplyQueryService = supplyQueryService;
    }

    @PostMapping
    public ResponseEntity<SupplyResource> createSupply(@RequestBody CreateSupplyResource resource) {
        var command = CreateSupplyCommandFromResourceAssembler.toCommandFromResource(resource);
        Long supplyId = supplyCommandService.createSupply(command);
        var supply = supplyQueryService.getSupplyById(supplyId);
        if (supply == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        var supplyResource = SupplyResourceFromEntityAssembler.toResourceFromEntity(supply);
        return new ResponseEntity<>(supplyResource, HttpStatus.CREATED);
    }

    @GetMapping("/{supplyId}")
    public ResponseEntity<SupplyResource> getSupplyById(@PathVariable Long supplyId) {
        Supply supply = supplyQueryService.getSupplyById(supplyId);
        if (supply == null) {
            return ResponseEntity.notFound().build();
        }
        SupplyResource supplyResource = SupplyResourceFromEntityAssembler.toResourceFromEntity(supply);
        return ResponseEntity.ok(supplyResource);
    }

    @GetMapping
    public ResponseEntity<List<SupplyResource>> getAllSupplies() {
        List<Supply> supplies = supplyQueryService.getAllSupplies();
        List<SupplyResource> supplyResources = supplies.stream()
                .map(SupplyResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(supplyResources);
    }

    @PutMapping("/{supplyId}")
    public ResponseEntity<SupplyResource> updateSupply(@PathVariable Long supplyId, @RequestBody CreateSupplyResource resource) {
        UpdateSupplyCommand command = new UpdateSupplyCommand(supplyId, resource.name(), resource.quantity());

        supplyCommandService.updateSupply(command);
        Supply updatedSupply = supplyQueryService.getSupplyById(supplyId);
        if (updatedSupply == null) {
            return ResponseEntity.notFound().build();
        }
        SupplyResource supplyResource = SupplyResourceFromEntityAssembler.toResourceFromEntity(updatedSupply);
        return ResponseEntity.ok(supplyResource);
    }

    @DeleteMapping("/{supplyId}")
    public ResponseEntity<Void> deleteSupply(@PathVariable Long supplyId) {
        try {
            DeleteSupplyCommand command = new DeleteSupplyCommand(supplyId);
            supplyCommandService.deleteSupply(command);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

}