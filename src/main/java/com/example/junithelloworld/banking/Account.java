package com.example.junithelloworld.banking;

public class Account {
    private String name;
    private Balance balance;

    public Account(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Balance getBalance() {
        return balance;
    }
}