package com.example.jobsearch.profile;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.example.jobsearch.answer.Answer;
import com.example.jobsearch.criterion.Criterion;

public class AnswerContainer {
    private Map<Integer, Answer> answers;

    public AnswerContainer() {
        answers = new HashMap<>();
    }

    public Answer put(Answer answer) {
        return answers.put(answer.getQuestion().getId(), answer);
    }
    
    public void put(Answer... answers) {
        for (Answer answer : answers) put(answer);
    }

    public void clearAll() {
        answers.clear();
    }

    public Answer getAnswerByCriterion(Criterion criterion) 
    throws IllegalArgumentException {
        Answer desirableAnswer = criterion.getDesirableAnswer();
        int idOfQuestionCorrespondingToCriterion = 
            desirableAnswer.getQuestion().getId();
        return getAnswerByQuestionId(idOfQuestionCorrespondingToCriterion);        
    }

    public Answer getAnswerByQuestionId(int questionId) 
    throws IllegalArgumentException {
        Answer expectedAnswer = answers.get(questionId);
        if (expectedAnswer != null) return expectedAnswer;
        throw new IllegalArgumentException(
            "There is no answer for this question in the container!");         
    }

    public Set<Answer> filterBy(Predicate<Answer> predicate) {
        return answers.values().stream().filter(predicate)
            .collect(Collectors.toSet());
    }
}