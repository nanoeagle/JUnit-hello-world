package com.example.junithelloworld.geometry;

import org.hamcrest.*;

public class RestrictsSides extends TypeSafeMatcher<Rectangle> {
    private int maxLength;

    public RestrictsSides(int maxLength) {
        this.maxLength = maxLength;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText(
            "The sides must not be greater than " + maxLength + ".");
    }
        
    @Override
    protected boolean matchesSafely(Rectangle actualRect) {
        return 
            Math.abs(actualRect.getOrigin().x - actualRect.getOpposite().x) <= maxLength &&
            Math.abs(actualRect.getOrigin().y - actualRect.getOpposite().y) <= maxLength;
    }
    
    @Factory
    public static Matcher<Rectangle> restrictsSidesTo(int maxLength) {
        return new RestrictsSides(maxLength);
    }
}