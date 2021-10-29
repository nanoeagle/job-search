package com.example.jobsearch.containers;

import java.util.*;

import com.example.jobsearch.answer.Answer;

public class AnswerContainer {
    private Map<String, Answer> answers;

    public AnswerContainer() {
        answers = new HashMap<>();
    }

    public Answer put(Answer answer) {
        return answers.put(answer.getQuestionText(), answer);
    }

    // need to be handled null return. 
    public Answer getByQuestionText(String questionText) {
        return answers.get(questionText);
    }
}