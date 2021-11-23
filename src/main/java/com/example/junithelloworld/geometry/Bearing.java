package com.example.junithelloworld.geometry;

public class Bearing {
    public static final int MAX_VALUE = 359;
    
    private int value;

    public Bearing(int value) {
        if (value < 0 || value > MAX_VALUE) 
            throw new BearingOutOfRangeException(
                "The bearing's value is not within " +
                "the valid range (0-" + MAX_VALUE + ")");
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public int calculateAngleTo(Bearing bearing) { 
        return value - bearing.value; 
    }
}