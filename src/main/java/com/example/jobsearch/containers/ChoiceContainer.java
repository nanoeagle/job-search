package com.example.jobsearch.containers;

import java.util.*;

public class ChoiceContainer {
    private List<String> choices;

    public ChoiceContainer() {
        choices = new ArrayList<>();
    }

    public boolean add(String choice) {
        return choices.add(choice);
    }

    public String getChoiceAt(int i) { 
        return choices.get(i); 
    }

    public int indexOf(String choice) {
        return choices.indexOf(choice);
    }
}