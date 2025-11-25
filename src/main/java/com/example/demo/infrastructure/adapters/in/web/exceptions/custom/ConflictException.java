package com.example.demo.infrastructure.adapters.in.web.exceptions.custom;

public class ConflictException extends RuntimeException {
    public ConflictException(String message) {
        super(message);
    }
}
