package com.example.jobsearch.calc;

public enum BasicScore {
    NOT_FULLFILL_A_BASIC_CRITERION(-1),
    NOT_FULLFILL_A_NONBASIC_CRITERION(0),
    NOT_FULLFILL_ANY_CRITERION(0);

    private int value;
    private BasicScore(int value) { this.value = value; }
    public int getValue() { return value; }
}