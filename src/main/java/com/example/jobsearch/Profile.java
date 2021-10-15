package com.example.jobsearch;

import com.example.jobsearch.collectionwrappers.AnswerMapWrapper;

public class Profile {
    private String name;
    private AnswerMapWrapper answers;

    public Profile(String name) {
        this.name = name;
        answers = new AnswerMapWrapper();
    }

    public String getName() { return name; }
    public AnswerMapWrapper getAnswers() { return answers; }
    
    public void setName(String name) { this.name = name; }
    public void setAnswers(AnswerMapWrapper answers) { this.answers = answers; }
}