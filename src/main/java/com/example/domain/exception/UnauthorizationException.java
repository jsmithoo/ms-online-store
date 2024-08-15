package com.example.domain.exception;

public class UnauthorizationException extends RuntimeException {

    public UnauthorizationException(String message) {
        super(message);
    }
}
