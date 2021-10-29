package com.example.jobsearch.containers;

import java.util.*;

import com.example.jobsearch.calc.MeanCalculator;

public class ScoreContainer implements MeanCalculator {

    private List<Integer> scores;

    public ScoreContainer() {
        scores = new ArrayList<>();
    }

    public boolean add(Integer score) {
        return scores.add(score);
    }

    public boolean remove(Integer score) {
        return scores.remove(score);
    }
    
    @Override
    public double calculateArithmeticMean() {
        return scores.stream().mapToInt(i -> i).average().orElse(0d);
    }

    @Override
    public double calculateGeometricMean() {
        int product = scores.stream().mapToInt(i -> i)
            .reduce(1, (p, factor) -> p * factor);
        return Math.pow(product, 1.0d / scores.size());
    }
}