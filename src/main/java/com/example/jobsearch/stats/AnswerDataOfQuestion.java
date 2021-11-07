package com.example.jobsearch.stats;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

import com.example.jobsearch.answer.Answer;
import com.example.jobsearch.exception.ExceptionMessages;

public class AnswerDataOfQuestion {
    private int questionId;
    private Map<Answer, AtomicInteger> theNumberOfAnswersEachKind;

    public AnswerDataOfQuestion(int questionId) {
        this.questionId = questionId;
        theNumberOfAnswersEachKind = new HashMap<>();
    }

    public Map<Answer, AtomicInteger> getCopyOfDataMap() {
        return new HashMap<>(theNumberOfAnswersEachKind);
    }

    public int getTheNumberOfAnswersEquivalentTo(Answer answer) 
    throws IllegalArgumentException {
        if (answerIsForTheQuestionWhoseTheData(answer)) {
            AtomicInteger theNumberOfEquivalentAnswers 
                = theNumberOfAnswersEachKind.get(answer);
            if (theNumberOfEquivalentAnswers == null) return 0;
            return theNumberOfEquivalentAnswers.get();
        }
        throw new IllegalArgumentException(
            ExceptionMessages.ILLEGAL_ANSWER_ARGUMENT.getValue());
    }

    private boolean answerIsForTheQuestionWhoseTheData(Answer answer) {
        return answer.getQuestion().getId() == questionId;
    }

    public void add(List<Answer> answers) {
        answers.stream()
            .filter(a -> answerIsForTheQuestionWhoseTheData(a))
            .forEach(this::add);
    }

    private void add(Answer answer) {
        if (dataAlreadyContains(answer)) {
            increaseTheNumberOfAnswersEquivalentTo(answer);
        } else {
            putNewAnswerIntoData(answer);
        }
    }

    private boolean dataAlreadyContains(Answer answer) {
        return theNumberOfAnswersEachKind.containsKey(answer);
    }

    private void increaseTheNumberOfAnswersEquivalentTo(Answer answer) {
        theNumberOfAnswersEachKind.get(answer).incrementAndGet();
    }

    private void putNewAnswerIntoData(Answer newAnswer) {
        theNumberOfAnswersEachKind.put(newAnswer, new AtomicInteger(1));
    }
}