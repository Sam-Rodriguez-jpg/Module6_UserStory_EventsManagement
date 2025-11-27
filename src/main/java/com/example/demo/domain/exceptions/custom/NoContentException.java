package com.example.demo.domain.exceptions.custom;

public class NoContentException extends RuntimeException {
    public NoContentException(String message) {
        super(message);
    }
}
