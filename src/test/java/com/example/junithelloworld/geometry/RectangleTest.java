package com.example.junithelloworld.geometry;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static com.example.junithelloworld.geometry.RestrictsSides.*;

import org.junit.*;

public class RectangleTest {
    private Rectangle rectangle;

    @Before
    public void ensureThatRectangleInvariant() {
        rectangle = new Rectangle(new Point(5, 5), new Point (15, 10));
        assertThat(rectangle, restrictsSidesTo(100));
    }

    @Test
    public void findingAreaResultsInExpectedValue() {
        assertThat(rectangle.findArea(), equalTo(50));
    }
}