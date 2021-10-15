package com.example.jobsearch.questions;

import com.example.jobsearch.collectionwrappers.ChoicesListWrapper;

public class BooleanQuestion extends Question {
    public BooleanQuestion(int id, String text) {
        super(id, text, new ChoicesListWrapper());
        getChoices().add("No");
        getChoices().add("Yes");
    }

    // @Override
    // public boolean matches(int expected, int actual) {
    //     return expected == actual;
    // }
}