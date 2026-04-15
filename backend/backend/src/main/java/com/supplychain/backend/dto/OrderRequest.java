package com.supplychain.backend.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class OrderRequest {

    @NotBlank(message = "Product ID is required")
    private String productId;

    @NotNull(message = "Quantity is required")
    @Min(value = 1, message = "Quantity must be greater than 0")
    private Integer quantity;

    @NotNull(message = "Order date is required")
    private LocalDate orderDate;

    private LocalDate deliveryDate;

    @NotNull(message = "Warehouse ID is required")
    private Integer warehouseId;

    @NotBlank(message = "Order status is required")
    private String orderStatus;
}