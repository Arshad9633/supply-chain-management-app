package com.supplychain.backend.model;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "inventory")
public class Inventory {

    @Id
    private String id;

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

    private LocalDateTime lastUpdated;
}
