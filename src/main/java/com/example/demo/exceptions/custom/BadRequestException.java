package com.example.demo.exceptions.custom;

public class BadRequestException extends IllegalArgumentException {
    public BadRequestException(String message) {
        super(message);
    }
}
