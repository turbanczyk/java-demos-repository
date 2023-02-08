package com.example.springbootbackend.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * A DTO for the {@link com.example.springbootbackend.entity.Product} entity
 */
@NoArgsConstructor
@Getter
@Setter
public class ProductDto implements Serializable {
    private String id;
    @NotNull
    private String productName;
    @NotNull
    private String productLineId;
    @NotNull
    private String productScale;
    @NotNull
    private String productVendor;
    @NotNull
    private String productDescription;
    @NotNull
    private Short quantityInStock;
    @NotNull
    private BigDecimal buyPrice;
    @NotNull
    private BigDecimal msrp;
}