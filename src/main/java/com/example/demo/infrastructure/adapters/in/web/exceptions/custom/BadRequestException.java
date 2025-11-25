package com.example.demo.infrastructure.adapters.in.web.exceptions.custom;

public class BadRequestException extends IllegalArgumentException {
    public BadRequestException(String message) {
        super(message);
    }
}
