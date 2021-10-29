package com.example.jobsearch.criterion;

import java.util.*;

import com.example.jobsearch.containers.ScoreContainer;

public class Criteria implements Iterable<Criterion> {

    private Set<Criterion> criteria;
    private ScoreContainer scoreSet;

    public Criteria() {
        criteria = new HashSet<>();
    }

    public boolean add(Criterion criterion) {
        return criteria.add(criterion);
    }

    public boolean remove(Criterion criterion) {
        return criteria.remove(criterion);
    }

    public ScoreContainer getScoreSet() {
        return scoreSet;
    }

    @Override
    public Iterator<Criterion> iterator() {
        return criteria.iterator();
    }
}