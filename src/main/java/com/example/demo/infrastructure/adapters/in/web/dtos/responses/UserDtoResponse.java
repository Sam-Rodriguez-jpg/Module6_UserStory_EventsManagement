package com.example.demo.infrastructure.adapters.in.web.dtos.responses;

public record UserDtoResponse(
        Long id,
        String username,
        String role
) {
}
