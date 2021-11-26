package com.example.junithelloworld.transmission;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.*;

public class TransmissionTest {
    private Transmission transmission;
    private Car car;

    @Before
    public void init() {
        car = new Car();
        transmission = new Transmission(car);
    }

    @Test
    public void remainsInGearDriveAfterAcceleration() {
        transmission.shiftTo(Gear.DRIVE);
        car.accelerateTo(35);
        assertThat(transmission.getGear(), equalTo(Gear.DRIVE));
    }

    @Test
    public void ignoresGearParkWhileMoving() {
        transmission.shiftTo(Gear.DRIVE);
        car.accelerateTo(30);
        transmission.shiftTo(Gear.PARK);
        assertThat(transmission.getGear(), equalTo(Gear.DRIVE));
    }

    @Test
    public void allowsGearToBeShiftedToParkWhenNotMoving() {
        transmission.shiftTo(Gear.DRIVE);
        car.accelerateTo(30);
        car.brakeToStop();
        transmission.shiftTo(Gear.PARK);
        assertThat(transmission.getGear(), equalTo(Gear.PARK));
    }
}