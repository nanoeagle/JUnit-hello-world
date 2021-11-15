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
    public void depositingUnrealDepositThrowsExpectedException() {
        long unrealDeposit = Math.round(Math.random() * (-1));
        balance.deposit(unrealDeposit);
    }

    @Test
    public void depositingUnrealDepositThrowsExpectedException_Assert() {
        assertThrows(IneligibleDepositException.class, () -> {
            long unrealDeposit = Math.round(Math.random() * (-1));
            balance.deposit(unrealDeposit);
        });
    }

    @Test
    public void depositingUnrealDepositNotifiesExpectedMessage() {
        String expectedMes = 
            ExceptionMessage.INELIGIBLE_DEPOSIT.getValue();
        try {
            long unrealDeposit = Math.round(Math.random() * (-1));
            balance.deposit(unrealDeposit);
            fail("Does not get the expected exception.");
        } catch (IneligibleDepositException e) {
            MatcherAssert.assertThat(e.getMessage(), 
                equalTo(expectedMes));
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
    public void overdrawingThrowsExpectedException() {
        balance.withdraw(money);
    }

    @Test
    public void overdrawingThrowsExpectedException_Assert() {
        assertThrows(InsufficientFundsException.class, 
            () -> balance.withdraw(money));
    }

    @Test
    public void overdrawingNotifiesExpectedMessage() {
        String expectedMes = 
            ExceptionMessage.INSUFFICIENT_FUNDS.getValue();
        try {
            balance.withdraw(money);
            fail("Does not get the expected exception.");
        } catch (InsufficientFundsException e) {
            MatcherAssert.assertThat(e.getMessage(), 
                equalTo(expectedMes));
        }
    }
}