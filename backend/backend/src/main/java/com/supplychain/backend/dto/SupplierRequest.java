package com.supplychain.backend.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SupplierRequest {

    @NotBlank(message = "Supplier name is required")
    private String supplierName;

    @NotBlank(message = "Country is required")
    private String country;

    @NotNull(message = "Lead time is required")
    @Min(value = 0, message = "Lead time cannot be negative")
    private Integer leadTimeDays;

    @NotNull(message = "Rating is required")
    @Min(value = 0, message = "Rating cannot be less than 0")
    @Max(value = 5, message = "Rating cannot be greater than 5")
    private Double rating;
}