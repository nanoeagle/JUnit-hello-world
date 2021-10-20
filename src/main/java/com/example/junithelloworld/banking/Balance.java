package com.example.junithelloworld.banking;

public class Balance {
    private int value;

    public int getValue() {
        return value;
    }

    public boolean isPositive() {
        return value > 0;
    }

    public void deposit(int deposit) {
        if (deposit <= 0) {
            throw new IneligibleDepositsException(
                "The deposit is not valid, must be positive"
            );
        }
        value += deposit;
    }

    public void withdraw(int withdrawnMoney) {
        if (value < withdrawnMoney) {
            throw new InsufficientFundsException(
                "The current balance is only " + value +
                "and insufficient to withdraw."
            );
        }
        value -= withdrawnMoney;
    }
}