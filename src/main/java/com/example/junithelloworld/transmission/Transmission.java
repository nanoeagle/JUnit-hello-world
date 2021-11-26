package com.example.junithelloworld.transmission;

public class Transmission {
    private Gear gear;
    private Moveable vehicle;

    public Transmission(Moveable vehicle) {
        this.vehicle = vehicle;
    }

    public Gear getGear() {
        return gear;
    }

    public void shiftTo(Gear gear) {
        if (gear == Gear.PARK && vehicle.isMoving()) return; 
        this.gear = gear;
    }
}