package com.bonifacio.app.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity(name = "roles")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Roles {
    @GeneratedValue(strategy = GenerationType.UUID)
    @Id
    private UUID id;
    @Column
    @NotNull
    @Size(max = 150)
    private String  name;
    @Column()
    @NotNull
    @Size(max = 10,min = 2)
    private String guardName;
}
