package com.example.junithelloworld.score;

import java.util.*;

public class ScoreCollection {
    private List<Scoreable> scores;
    
    public ScoreCollection() {
        scores = new ArrayList<>();
    }

    public void add(Scoreable scoreable) {
        if (scoreable == null) 
            throw new IllegalArgumentException(
                "The added scoreable instance must not be null!");
        scores.add(scoreable);
    }
    
    public double calculateArithmeticMean() {
        return scores.stream().mapToInt(Scoreable::getScore)
            .average().orElse(0d);
    }
}