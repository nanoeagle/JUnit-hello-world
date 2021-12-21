package com.example.junithelloworld.score;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import org.junit.*;

public class ScoreCollectionTest {
    private ScoreCollection collection;

    @Before
    public void init() {
        collection = new ScoreCollection();
    }

    @Test
    public void calculatingArithmeticMeanOfSomeScoresResultsInExpectedNumber() {
        double expectedNumber = createExpectedNumber();
        assertThat(collection.calculateArithmeticMean(), 
            equalTo(expectedNumber));
    }

    private double createExpectedNumber() {
        int[] scores = {Integer.MAX_VALUE, 7, 3};
        double sum = 0;
        for (int score : scores) {
            collection.add(() -> score);
            sum += score;
        }
        return sum / scores.length;
    }

    @Test(expected = IllegalArgumentException.class)
    public void addingNullthrowsException() {
        collection.add(null);
    }

    @Test
    public void calculatingArithmeticMeanOfEmptyListResultsInZero() {
        assertThat(collection.calculateArithmeticMean(), equalTo(0d));
    }
}