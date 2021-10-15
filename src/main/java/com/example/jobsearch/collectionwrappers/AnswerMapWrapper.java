package com.example.jobsearch.collectionwrappers;

import java.util.*;

import com.example.jobsearch.answer.Answer;

public class AnswerMapWrapper {
    private Map<String, Answer> answers = new HashMap<>();

    public void put(Answer answer) {
        answers.put(answer.getQuestionText(), answer);
    }

    // need to be handled null return. 
    public Answer getByQuestionText(String questionText) {
        return answers.get(questionText);
    }
}