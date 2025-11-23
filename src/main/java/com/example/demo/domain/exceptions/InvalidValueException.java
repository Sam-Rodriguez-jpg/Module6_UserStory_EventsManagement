package com.example.demo.domain.exceptions;

public class InvalidValueException extends DomainException {
    public InvalidValueException(String message) {
        super(message);
    }
}
