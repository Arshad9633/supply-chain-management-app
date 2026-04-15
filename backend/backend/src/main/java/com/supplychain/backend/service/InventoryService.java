package com.supplychain.backend.service;

import com.supplychain.backend.dto.InventoryRequest;
import com.supplychain.backend.dto.InventoryResponse;
import com.supplychain.backend.exception.BadRequestException;
import com.supplychain.backend.exception.ResourceNotFoundException;
import com.supplychain.backend.model.Inventory;
import com.supplychain.backend.repository.InventoryRepository;
import com.supplychain.backend.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class InventoryService {

    private final InventoryRepository inventoryRepository;
    private final ProductRepository productRepository;

    public InventoryService(InventoryRepository inventoryRepository, ProductRepository productRepository) {
        this.inventoryRepository = inventoryRepository;
        this.productRepository = productRepository;
    }

    public InventoryResponse createInventory(InventoryRequest request) {
        if (!productRepository.existsById(request.getProductId())) {
            throw new ResourceNotFoundException("Product not found with id: " + request.getProductId());
        }

        Optional<Inventory> existingInventory = inventoryRepository
                .findByProductIdAndWarehouseId(request.getProductId(), request.getWarehouseId());

        if (existingInventory.isPresent()) {
            throw new BadRequestException("Inventory already exists for this product and warehouse");
        }

        Inventory inventory = new Inventory();
        inventory.setProductId(request.getProductId());
        inventory.setWarehouseId(request.getWarehouseId());
        inventory.setStockLevel(request.getStockLevel());
        inventory.setReorderThreshold(request.getReorderThreshold());
        inventory.setLastUpdated(LocalDateTime.now());

        Inventory savedInventory = inventoryRepository.save(inventory);
        return mapToResponse(savedInventory);
    }

    public List<InventoryResponse> getAllInventory() {
        return inventoryRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public InventoryResponse getInventoryById(String id) {
        Inventory inventory = inventoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Inventory not found with id: " + id));
        return mapToResponse(inventory);
    }

    public List<InventoryResponse> getInventoryByProductId(String productId) {
        return inventoryRepository.findByProductId(productId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public List<InventoryResponse> getInventoryByWarehouseId(Integer warehouseId) {
        return inventoryRepository.findByWarehouseId(warehouseId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public InventoryResponse updateInventory(String id, InventoryRequest request) {
        if (!productRepository.existsById(request.getProductId())) {
            throw new ResourceNotFoundException("Product not found with id: " + request.getProductId());
        }

        Inventory existingInventory = inventoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Inventory not found with id: " + id));

        Optional<Inventory> duplicateInventory = inventoryRepository
                .findByProductIdAndWarehouseId(request.getProductId(), request.getWarehouseId());

        if (duplicateInventory.isPresent() && !duplicateInventory.get().getId().equals(id)) {
            throw new BadRequestException("Another inventory record already exists for this product and warehouse");
        }

        existingInventory.setProductId(request.getProductId());
        existingInventory.setWarehouseId(request.getWarehouseId());
        existingInventory.setStockLevel(request.getStockLevel());
        existingInventory.setReorderThreshold(request.getReorderThreshold());
        existingInventory.setLastUpdated(LocalDateTime.now());

        Inventory updatedInventory = inventoryRepository.save(existingInventory);
        return mapToResponse(updatedInventory);
    }

    public void deleteInventory(String id) {
        if (!inventoryRepository.existsById(id)) {
            throw new ResourceNotFoundException("Inventory not found with id: " + id);
        }
        inventoryRepository.deleteById(id);
    }

    public List<InventoryResponse> getLowStockItems() {
        return inventoryRepository.findAll()
                .stream()
                .filter(item -> item.getStockLevel() <= item.getReorderThreshold())
                .map(this::mapToResponse)
                .toList();
    }

    private InventoryResponse mapToResponse(Inventory inventory) {
        return new InventoryResponse(
                inventory.getId(),
                inventory.getProductId(),
                inventory.getWarehouseId(),
                inventory.getStockLevel(),
                inventory.getReorderThreshold(),
                inventory.getLastUpdated()
        );
    }
}