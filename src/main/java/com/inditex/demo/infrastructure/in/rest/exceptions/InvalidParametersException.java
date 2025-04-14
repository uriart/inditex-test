package com.inditex.demo.infrastructure.in.rest.exceptions;

public class InvalidParametersException extends RuntimeException {

    public InvalidParametersException(String message) {
        super(message);
    }
}