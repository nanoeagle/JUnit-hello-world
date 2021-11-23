package com.example.junithelloworld.geometry;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import org.junit.*;

public class BearingTest {
    @Test(expected = BearingOutOfRangeException.class)
    public void creatingBearingByNegativeValueThrowsException() {
        new Bearing(-1);
    }

    @Test(expected = BearingOutOfRangeException.class)
    public void creatingBearingByValueLargerThanMaxThrowsException() {
        new Bearing(Bearing.MAX_VALUE + 1);
    }
    
    @Test
    public void calculatingAngleBetweenBearingsResultsInExpectedValue() {
        assertThat(new Bearing(15).calculateAngleTo(new Bearing(12)), 
            equalTo(3));
    }
    
    @Test
    public void calculatingAngleToLargerBearingResultsInNegativeValue() {
        assertThat(new Bearing(12).calculateAngleTo(new Bearing(15)),
            equalTo(-3));
    }
}