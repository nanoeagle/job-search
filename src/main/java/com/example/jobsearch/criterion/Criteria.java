package com.example.jobsearch.criterion;

import java.util.*;

public class Criteria implements Iterable<Criterion> {

    private Set<Criterion> criteria;

    public Criteria() {
        criteria = new HashSet<>();
    }

    public boolean add(Criterion criterion) {
        return criteria.add(criterion);
    }

    public boolean remove(Criterion criterion) {
        return criteria.remove(criterion);
    }

    @Override
    public Iterator<Criterion> iterator() {
        return criteria.iterator();
    }
}