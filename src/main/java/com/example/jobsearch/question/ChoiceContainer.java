package com.example.jobsearch.question;

import java.util.*;

public class ChoiceContainer {
    private List<String> choices;

    public ChoiceContainer() {
        choices = new ArrayList<>();
    }

    public boolean add(String choice) {
        return choices.add(choice);
    }

    public String getChoiceAt(int index) { 
        return choices.get(index); 
    }

    public int indexOf(String choice) {
        return choices.indexOf(choice);
    }
}