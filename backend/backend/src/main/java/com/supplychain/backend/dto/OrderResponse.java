package com.supplychain.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class OrderResponse {
    private String id;
    private String productId;
    private Integer quantity;
    private LocalDate orderDate;
    private LocalDate deliveryDate;
    private Integer warehouseId;
    private String orderStatus;
}