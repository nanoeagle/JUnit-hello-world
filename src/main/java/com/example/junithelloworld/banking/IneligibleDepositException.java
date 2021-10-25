package com.example.junithelloworld.banking;

public class IneligibleDepositException extends RuntimeException {
    public IneligibleDepositException(String message) {
        super(message);
    }
}