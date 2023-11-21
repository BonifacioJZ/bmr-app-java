package com.bonifacio.app.services.dao;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductInDto {
    @NotNull
    @Size(max = 150)
    private String name;
    @Size(max = 500)
    private String description;
    private boolean is_enable;

    @DecimalMin(value = "0.0",inclusive = false)
    @Digits(integer = 9,fraction = 2)
    private BigDecimal price ;

    @NotNull
    private UUID categoryId;
}
