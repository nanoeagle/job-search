package com.example.jobsearch.question;

public abstract class Question {
    private int id;
    private String text;
    private ChoiceContainer choices;
    
    public Question(int id, String content, ChoiceContainer choices) {
        this.id = id;
        this.text = content;
        this.choices = choices;
    }

    public int getId() { return id; }
    public String getText() { return text; }
    public ChoiceContainer getChoices() { return choices; }
}