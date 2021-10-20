package com.example.junithelloworld.banking;

import static org.junit.Assert.*;

import org.junit.*;

public class BalanceTest {
    private final int depositedMoney = 50;
    
    private Balance balance;
    
    @Before
    public void init() {
        balance = new Balance();
    }

    @Test
    public void testIsPositive() {
        balance.deposit(depositedMoney);
        assertTrue(balance.isPositive());
    }

    @Test
    public void depositingIncreasesBalanceValue() {
        int initialValue = balance.getValue();
        balance.deposit(depositedMoney);
        int laterValue = balance.getValue();
        assertTrue(laterValue > initialValue);
    }
}