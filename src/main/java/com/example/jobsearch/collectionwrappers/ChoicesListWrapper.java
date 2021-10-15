package com.example.jobsearch.collectionwrappers;

import java.util.*;

public class ChoicesListWrapper {
    private List<String> choices = new ArrayList<>();

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