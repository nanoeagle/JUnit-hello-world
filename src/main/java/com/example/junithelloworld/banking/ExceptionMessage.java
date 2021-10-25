package com.example.junithelloworld.banking;

public enum ExceptionMessage {
    INELIGIBLE_DEPOSIT("The deposit must be positive."),
    INSUFFICIENT_FUNDS("The current balance is insufficient to withdraw.");

    private String value;

    private ExceptionMessage(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}