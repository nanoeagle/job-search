package com.example.jobsearch.profile;

public class Profile {
    private int id;
    private String name;
    private AnswerContainer answers;

    public Profile(int id, String name) {
        this.id = id;
        this.name = name;
        answers = new AnswerContainer();
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public AnswerContainer getAnswers() { return answers; }
    
    public void setName(String name) { this.name = name; }
    public void setAnswers(AnswerContainer answers) { 
        this.answers = answers;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        return id == ((Profile) obj).id;
    }

    @Override
    public String toString() {
        return id + " - " + name;
    }
}