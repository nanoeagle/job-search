package com.example.jobsearch.question;

import java.util.*;

public class ChoiceContainer {
    private List<String> choices;

    public ChoiceContainer() {
        choices = new ArrayList<>();
    }

    public ChoiceContainer(String[] predefinedChoices) {
        choices = new ArrayList<>(Arrays.asList(predefinedChoices));
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

    public boolean isChoiceIndexWithinBounds(int index) {
        if (index >= 0 && index < choices.size()) return true;
        return false;
    }

    public int size() {
        return choices.size();
    }
}