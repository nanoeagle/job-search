package com.example.jobsearch.criterion;

public enum Importance {
    TRIVIAL(1),
    INTERESTING(2),
    IMPORTANT(3),
    VERYIMPORTANT(5),
    CRITICAL(10);
     
    private int value;
    private Importance(int value) { this.value = value; }
    public int getValue() { return value; }
}