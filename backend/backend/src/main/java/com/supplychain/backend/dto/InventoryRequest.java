package com.supplychain.backend.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class InventoryRequest {

    @NotBlank(message = "Product ID is required")
    private String productId;

    @NotNull(message = "Warehouse ID is required")
    private Integer warehouseId;

    @NotNull(message = "Stock level is required")
    @Min(value = 0, message = "Stock level cannot be negative")
    private Integer stockLevel;

    @NotNull(message = "Reorder threshold is required")
    @Min(value = 0, message = "Reorder threshold cannot be negative")
    private Integer reorderThreshold;
}