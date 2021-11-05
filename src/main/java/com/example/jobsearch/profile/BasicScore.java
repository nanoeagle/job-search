package com.example.jobsearch.profile;

public enum BasicScore {
    NOT_FULLFILL_BASIC_CRITERION(-1);

    private int value;
    private BasicScore(int value) { this.value = value; }
    public int getValue() { return value; }
}