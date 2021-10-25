package com.example.junithelloworld.banking;

public class Balance {
    private long value;

    public long getValue() {
        return value;
    }

    public boolean isPositive() {
        return value > 0;
    }

    public void deposit(long deposit) {
        if (deposit <= 0) {
            throw new IneligibleDepositException(
                ExceptionMessage.INELIGIBLE_DEPOSIT.getValue()
            );
        }
        value += deposit;
    }

    public void withdraw(long withdrawnMoney) {
        if (value < withdrawnMoney) {
            throw new InsufficientFundsException(
                ExceptionMessage.INSUFFICIENT_FUNDS.getValue()
            );
        }
        value -= withdrawnMoney;
    }
}