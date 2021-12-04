package com.example.jobsearch.criterion;

public enum Importance {
    TRIVIAL(1),
    INTERESTING(3),
    IMPORTANT(5),
    VERYIMPORTANT(7),
    CRITICAL(10);
     
    private int value;
    private Importance(int value) { this.value = value; }
    public int getVal() { return value; }
}