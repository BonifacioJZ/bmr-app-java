package com.bonifacio.app.controllers;

import com.bonifacio.app.response.CustomResponse;
import com.bonifacio.app.services.AuthService;
import com.bonifacio.app.services.dao.LoginRequest;
import com.bonifacio.app.services.dao.RegisterRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth/")
@Tag(name = "Authentication")
@AllArgsConstructor
public class AuthController {
        private final AuthService authService;

        @Operation(summary = "Login to system", description = "The `login` method is a POST request handler for logging in a user to the system.")
        @PostMapping("login/")
        // The `login` method is a POST request handler for logging in a user to the
        // system.
        public ResponseEntity<CustomResponse<Object>> login(@Valid @RequestBody LoginRequest loginRequest,
                        BindingResult result) {
                if (result.hasErrors())
                        return new ResponseEntity<>(CustomResponse.builder()
                                        .success(false)
                                        .message("error to login")
                                        .data(result.getFieldError())
                                        .build(), HttpStatus.BAD_REQUEST);
                var token = authService.login(loginRequest);
                return new ResponseEntity<>(CustomResponse.builder()
                                .success(true)
                                .message("logged")
                                .data(token)
                                .build(), HttpStatus.OK);

        }

        @PostMapping("register/")
        @Operation(summary = "Create a new user to system", description = "The `register` method is a POST request handler for registering a new user in the system.")
        @SecurityRequirements()
        // The `register` method is a POST request handler for registering a new user in
        // the system.
        public ResponseEntity<CustomResponse<Object>> register(@Valid @RequestBody RegisterRequest request,
                        BindingResult result) {
                if (result.hasErrors())
                        return new ResponseEntity<>(CustomResponse.builder()
                                        .success(false)
                                        .message("Error to Register User")
                                        .data(result.getFieldError())
                                        .build(), HttpStatus.BAD_REQUEST);
                var token = authService.register(request);
                return new ResponseEntity<>(CustomResponse.builder()
                                .success(true)
                                .message("Created")
                                .data(token)
                                .build(), HttpStatus.OK);
        }
}
