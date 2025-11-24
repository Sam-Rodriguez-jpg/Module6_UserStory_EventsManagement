package com.example.demo.exceptions.custom;

public class NoContentException extends RuntimeException {
    public NoContentException(String message) {
        super(message);
    }
}
