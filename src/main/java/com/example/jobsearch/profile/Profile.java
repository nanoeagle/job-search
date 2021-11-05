package com.example.jobsearch.profile;

public class Profile {
    private String name;
    private AnswerContainer answers;

    public Profile(String name) {
        this.name = name;
        answers = new AnswerContainer();
    }

    public String getName() { return name; }
    public AnswerContainer getAnswers() { return answers; }
    
    public void setName(String name) { this.name = name; }
    public void setAnswers(AnswerContainer answers) { this.answers = answers; }
}