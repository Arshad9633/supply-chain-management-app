package com.supplychain.backend.model;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "products")
public class Product {

    @Id
    private String id;

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
