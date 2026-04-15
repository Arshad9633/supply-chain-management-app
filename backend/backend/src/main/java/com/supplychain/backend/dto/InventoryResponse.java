package com.supplychain.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class InventoryResponse {
    private String id;
    private String productId;
    private Integer warehouseId;
    private Integer stockLevel;
    private Integer reorderThreshold;
    private LocalDateTime lastUpdated;
}