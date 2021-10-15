package com.example.jobsearch.criterion;

public enum Weight {
    CRITICAL(10),
    VERYIMPORTANT(5),
    IMPORTANT(3),
    INTERESTING(2),
    TRIVIAL(1);
     
    private int value;

    private Weight(int value) { this.value = value; }
    
    public int getValue() { return value; }
}