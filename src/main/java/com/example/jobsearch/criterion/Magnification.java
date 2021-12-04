package com.example.jobsearch.criterion;

public enum Magnification {
    NONE(1),
    FIVE_TIMES(5),
    TEN_TIMES(10);
     
    private int value;
    private Magnification(int value) { this.value = value; }
    public int getVal() { return value; }
}