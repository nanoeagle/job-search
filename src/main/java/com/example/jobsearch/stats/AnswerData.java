package com.example.jobsearch.stats;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

import com.example.jobsearch.answer.Answer;

public class AnswerData {
    private Map<Answer, AtomicInteger> theNumberOfAnswersPerChoice;

    public AnswerData() {
        theNumberOfAnswersPerChoice = new HashMap<>();
    }

    public Map<Answer, AtomicInteger> getCopyOfData() {
        return Map.copyOf(theNumberOfAnswersPerChoice);
    }

    public AtomicInteger getDataOfAnswerEquivalentTo(Answer answer) {
        return theNumberOfAnswersPerChoice.get(answer);
    }
    
    public void add(Answer answer) {
        if (theNumberOfAnswersPerChoice.containsKey(answer)) {
            increaseTheNumberOfAnswersEquivalentTo(answer);
        } else {
            putNewAnswerIntoData(answer);
        }
    }

    private void increaseTheNumberOfAnswersEquivalentTo(Answer answer) {
        theNumberOfAnswersPerChoice.get(answer).incrementAndGet();
    }

    private void putNewAnswerIntoData(Answer newAnswer) {
        theNumberOfAnswersPerChoice.put(newAnswer, new AtomicInteger(1));
    }
}