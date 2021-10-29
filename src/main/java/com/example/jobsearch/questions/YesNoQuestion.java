package com.example.jobsearch.questions;

import com.example.jobsearch.containers.ChoiceContainer;

public class YesNoQuestion extends Question {
    public YesNoQuestion(int id, String text) {
        super(id, text, new ChoiceContainer());
        getChoices().add("No");
        getChoices().add("Yes");
    }
}