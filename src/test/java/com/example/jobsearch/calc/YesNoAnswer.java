package com.example.jobsearch.calc;

public enum YesNoAnswer {
    NO(0),
    YES(1);

    private int index;
    private YesNoAnswer(int index) { this.index = index; }
    public int getIndex() { return index; }
}