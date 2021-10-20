package com.example.junithelloworld.banking;

public class IneligibleDepositsException extends RuntimeException {
    public IneligibleDepositsException(String message) {
        super(message);
    }
}