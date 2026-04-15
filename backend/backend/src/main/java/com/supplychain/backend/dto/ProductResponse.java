package com.supplychain.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductResponse {
    private String id;
    private String productName;
    private String category;
    private Double unitCost;
    private String supplierId;
}