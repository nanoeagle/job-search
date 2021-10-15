package com.example.jobsearch.questions;

import com.example.jobsearch.collectionwrappers.ChoicesListWrapper;

public abstract class Question {
    private int id;
    private String text;
    private ChoicesListWrapper choices;
    
    public Question(int id, String text, ChoicesListWrapper choices) {
        this.id = id;
        this.text = text;
        this.choices = choices;
    }

    public int getId() { return id; }
    public String getText() { return text; }
    public ChoicesListWrapper getChoices() { return choices; }

    // public boolean match(Answer answer) {
    //     return false;
    // }
    
    // public abstract boolean matches(int expected, int actual);
}