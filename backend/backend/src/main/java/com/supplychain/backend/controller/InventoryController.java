package com.supplychain.backend.controller;

import com.supplychain.backend.dto.InventoryRequest;
import com.supplychain.backend.dto.InventoryResponse;
import com.supplychain.backend.service.InventoryService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@CrossOrigin(origins = "http://localhost:3000")
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @PostMapping
    public ResponseEntity<InventoryResponse> createInventory(@Valid @RequestBody InventoryRequest request) {
        return ResponseEntity.ok(inventoryService.createInventory(request));
    }

    @GetMapping
    public ResponseEntity<List<InventoryResponse>> getAllInventory() {
        return ResponseEntity.ok(inventoryService.getAllInventory());
    }

    @GetMapping("/{id}")
    public ResponseEntity<InventoryResponse> getInventoryById(@PathVariable String id) {
        return ResponseEntity.ok(inventoryService.getInventoryById(id));
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<InventoryResponse>> getInventoryByProductId(@PathVariable String productId) {
        return ResponseEntity.ok(inventoryService.getInventoryByProductId(productId));
    }

    @GetMapping("/warehouse/{warehouseId}")
    public ResponseEntity<List<InventoryResponse>> getInventoryByWarehouseId(@PathVariable Integer warehouseId) {
        return ResponseEntity.ok(inventoryService.getInventoryByWarehouseId(warehouseId));
    }

    @GetMapping("/low-stock")
    public ResponseEntity<List<InventoryResponse>> getLowStockItems() {
        return ResponseEntity.ok(inventoryService.getLowStockItems());
    }

    @PutMapping("/{id}")
    public ResponseEntity<InventoryResponse> updateInventory(@PathVariable String id,
                                                             @Valid @RequestBody InventoryRequest request) {
        return ResponseEntity.ok(inventoryService.updateInventory(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteInventory(@PathVariable String id) {
        inventoryService.deleteInventory(id);
        return ResponseEntity.ok("Inventory deleted successfully");
    }
}