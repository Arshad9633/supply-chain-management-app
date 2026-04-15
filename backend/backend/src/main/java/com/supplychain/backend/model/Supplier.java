package com.supplychain.backend.model;


import jakarta.validation.constraints.Max;
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
@Document(collection = "suppliers")
public class Supplier {

    @Id
    private String id;

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
