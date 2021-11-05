package com.example.jobsearch.profile;

import java.util.*;

import com.example.jobsearch.answer.Answer;

public class AnswerContainer {
    private Map<Integer, Answer> answers;

    public AnswerContainer() {
        answers = new HashMap<>();
    }

    public Answer put(Answer answer) {
        return answers.put(answer.getQuestion().getId(), answer);
    }

    public Answer getAnswerByQuestionId(int questionId) {
        return answers.get(questionId);
    }
}