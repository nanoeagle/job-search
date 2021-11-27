package com.example.jobsearch.stats;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

import com.example.jobsearch.answer.Answer;
import com.example.jobsearch.exception.ExceptionMessages;
import com.example.jobsearch.question.Question;

public class AnswerDataOfQuestion extends AnswerData {
    private Question question;

    public AnswerDataOfQuestion(Question question) {
        this.question = question;
    }

    public void add(List<Answer> answers) {
        answers.stream().filter(answer -> answer.isFor(question))
            .forEach(this::add);
    }

    public int getTheNumberOfAnswersEquivalentTo(Answer answer) 
    throws IllegalArgumentException {
        if (answer.isFor(question)) {
            AtomicInteger theNumberOfEquivalentAnswers 
                = getDataOfAnswerEquivalentTo(answer);
            if (theNumberOfEquivalentAnswers == null) return 0;
            return theNumberOfEquivalentAnswers.get();
        }
        throw new IllegalArgumentException(
            ExceptionMessages.ILLEGAL_ANSWER_ARGUMENT.getValue());
    }
}