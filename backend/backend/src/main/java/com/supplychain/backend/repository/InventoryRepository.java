package com.supplychain.backend.repository;

import com.supplychain.backend.model.Inventory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InventoryRepository extends MongoRepository<Inventory, String> {

    List<Inventory> findByProductId(String productId);

    List<Inventory> findByWarehouseId(Integer warehouseId);

    Optional<Inventory> findByProductIdAndWarehouseId(String productId, Integer warehouseId);

    List<Inventory> findByStockLevelLessThanEqual(Integer stockLevel);
}