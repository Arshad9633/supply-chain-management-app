package com.supplychain.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SupplierResponse {
    private String id;
    private String supplierName;
    private String country;
    private Integer leadTimeDays;
    private Double rating;
}