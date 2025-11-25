package com.example.demo.infrastructure.adapters.in.web.exceptions.custom;

public class NoContentException extends RuntimeException {
    public NoContentException(String message) {
        super(message);
    }
}
