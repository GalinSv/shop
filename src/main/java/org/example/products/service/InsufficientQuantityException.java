package org.example.products.service;

public class InsufficientQuantityException extends RuntimeException {
    public InsufficientQuantityException(String message) {
        super(message);
    }
}