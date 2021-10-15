    package com.example.jobsearch.answer;

import com.example.jobsearch.criterion.Criterion;
import com.example.jobsearch.questions.Question;

public class Answer {
    private int index;
    private Question question;

    public Answer(Question question, int index) {
        this.question = question;
        this.index = index;
    }

    public Answer(Question question, String answerChoice) {
        this.question = question;
        this.index = question.getChoices().indexOf(answerChoice);
    }
    
    public int getIndex() { return index; }
    public Question getQuestion() { return question; }
    public String getQuestionText() { return question.getText(); }

    public boolean matches(Answer otherAnswer) {
        return question.getId() == otherAnswer.getQuestion().getId() 
            && index == otherAnswer.index;
    }

    public boolean doesNotFulfillBasicCriterion(Criterion criterion) {
        return !criterion.isFulfilledBy(this) 
            && criterion.isBasicCriterion();
    }

    @Override
    public String toString() {
        return String.format(
            "%s %s", 
            question.getText(), 
            question.getChoices().getChoiceAt(index)
        );
    }
}