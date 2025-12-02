package com.example.demo.infrastructure.adapters.in.web.dtos.requests;

import jakarta.validation.constraints.NotBlank;

public record UserRegisterDtoRequest(
        @NotBlank(message = "username is required")
        String username,

        @NotBlank(message = "password is required")
        String password,
        String role
) {
}
