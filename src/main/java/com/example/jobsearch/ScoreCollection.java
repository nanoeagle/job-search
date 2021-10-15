package com.example.jobsearch;

import java.util.*;

public class ScoreCollection {
    private List<Scoreable> scores = new ArrayList<>();
    
    public void add(Scoreable scoreable) {
        scores.add(scoreable);
    }
    
    public double arithmeticMean() {
        return scores.stream().mapToInt(Scoreable::getScore)
            .average().orElse(0d);
    }
}