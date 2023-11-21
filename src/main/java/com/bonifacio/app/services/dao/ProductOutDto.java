package com.bonifacio.app.services.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProductOutDto implements Serializable {
    private UUID id;
    private String name;
    private String description;
    private boolean is_enable;
    private BigDecimal price;

}
