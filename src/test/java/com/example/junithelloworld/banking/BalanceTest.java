package com.example.junithelloworld.banking;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.hamcrest.MatcherAssert;
import org.junit.*;

public class BalanceTest {
    private final long money = 50;
    
    private Balance balance;
    
    @Before
    public void init() {
        balance = new Balance();
    }
    
    @Test
    public void balanceIsPositiveIfValueIsGreaterThanZero() {
        balance.deposit(money);
        assertTrue(balance.isPositive());
    }

    @Test
    public void balanceIsNotPositiveIfValueIsEqualToZero() {
        assertFalse(balance.isPositive());
    }
    
    @Test
    public void depositingIncreasesBalanceValue() {
        long initialValue = balance.getValue();
        balance.deposit(money);
        long laterValue = balance.getValue();
        assertTrue(laterValue > initialValue);
    }

    @Test(expected = IneligibleDepositException.class)
    public void throwIneligibleDepositExceptionWhenDepositIsNotPositive() {
        long notPositiveNumber = Math.round(Math.random() * (-1));
        balance.deposit(notPositiveNumber);
    }

    @Test
    public void throwIneligibleDepositExceptionWhenDepositIsNotPositive_Assert() {
        assertThrows(IneligibleDepositException.class, () -> {
            long notPositiveNumber = Math.round(Math.random() * (-1));
            balance.deposit(notPositiveNumber);
        });
    }

    @Test
    public void verifyExceptionMessageWhenDepositIsNotPositive() {
        try {
            long notPositiveNumber = Math.round(Math.random() * (-1));
            balance.deposit(notPositiveNumber);
            fail("Does not get the expected exception.");
        } catch (IneligibleDepositException expected) {
            MatcherAssert.assertThat(expected.getMessage(), equalTo(
                ExceptionMessage.INELIGIBLE_DEPOSIT.getValue()));
        }
    }

    @Test
    public void withdrawingDecreasesBalanceValue() {
        balance.deposit(money);
        long initialValue = balance.getValue();
        balance.withdraw(money);
        long laterValue = balance.getValue();
        assertFalse(laterValue > initialValue);
    }

    @Test(expected = InsufficientFundsException.class)
    public void throwInsufficientFundsExceptionWhenOverdrawing() {
        balance.withdraw(money);
    }

    @Test
    public void throwInsufficientFundsExceptionWhenOverdrawing_Assert() {
        assertThrows(InsufficientFundsException.class, () -> 
            balance.withdraw(money));
    }

    @Test
    public void verifyExceptionMessageWhenOverdrawing() {
        try {
            balance.withdraw(money);
            fail("Does not get the expected exception.");
        } catch (InsufficientFundsException expected) {
            MatcherAssert.assertThat(expected.getMessage(), equalTo(
                ExceptionMessage.INSUFFICIENT_FUNDS.getValue()));
        }
    }
}