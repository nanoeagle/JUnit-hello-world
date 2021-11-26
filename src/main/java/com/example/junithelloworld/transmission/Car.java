package com.example.junithelloworld.transmission;

public class Car implements Moveable {
    private int kmph;

    public void accelerateTo(int kmph) {
        this.kmph = kmph;
    }

    public void brakeToStop() {
        kmph = 0;
    }

    @Override
    public int getCurrentSpeedInKmph() {
        return kmph;
    }

    @Override
    public boolean isMoving() {
        return kmph > 0;
    }
}