package com.supplychain.backend.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProductRequest {

    @NotBlank(message = "Product name is required")
    private String productName;

    @NotBlank(message = "Category is required")
    private String category;

    @NotNull(message = "Unit cost is required")
    @Min(value = 0, message = "Unit cost cannot be negative")
    private Double unitCost;

    @NotBlank(message = "Supplier ID is required")
    private String supplierId;
}