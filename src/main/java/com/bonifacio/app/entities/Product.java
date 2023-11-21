package com.bonifacio.app.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.math.BigDecimal;
import java.util.UUID;

@Entity(name = "products")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column
    @NotBlank
    @NotNull
    @Size(max = 150)
    private String name;
    @Column
    @Size(max = 500)
    private String description;
    @Column
    private boolean is_enable = true;
    @Column
    @DecimalMin(value = "0.0",inclusive = false)
    @Digits(integer = 9,fraction = 2)
    private BigDecimal price;


    @JoinColumn(name = "category_id")
    @ManyToOne(targetEntity = Category.class)
    private Category category;

}