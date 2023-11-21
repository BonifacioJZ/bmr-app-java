package com.bonifacio.app.services.dao;

import com.bonifacio.app.validations.UniqueNameCategory;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategoryInDto implements Serializable {
    @NotNull
    @Size(max = 150)
    @UniqueNameCategory()
    private String name;
    @Size(max = 500)
    private String description;
}
