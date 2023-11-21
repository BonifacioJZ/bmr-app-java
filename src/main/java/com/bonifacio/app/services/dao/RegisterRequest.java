package com.bonifacio.app.services.dao;

import com.bonifacio.app.validations.UniqueEmail;
import com.bonifacio.app.validations.UniqueUsername;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegisterRequest {
    @NotNull
    @Size(max = 150)
    @UniqueUsername
    private String username;
    @NotNull
    @Email
    @UniqueEmail
    private String email;
    @NotNull
    @Size(max = 32,min =8)
    private String password;
}
