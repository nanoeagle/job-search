package com.example.jobsearch.questions;

import com.example.jobsearch.containers.ChoiceContainer;

public abstract class Question {
    private int id;
    private String text;
    private ChoiceContainer choices;
    
    public Question(int id, String text, ChoiceContainer choices) {
        this.id = id;
        this.text = text;
        this.choices = choices;
    }

    public int getId() { return id; }
    public String getText() { return text; }
    public ChoiceContainer getChoices() { return choices; }
}