package com.example.jobsearch.question;

public class YesNoQuestion extends Question {
    public YesNoQuestion(int id, String content) {
        super(id, content, new ChoiceContainer());
        getChoices().add("No");
        getChoices().add("Yes");
    }
}