package com.example.jobsearch.answer;

import com.example.jobsearch.criterion.Criterion;
import com.example.jobsearch.question.Question;

public class Answer {
    private int choiceIndexInQuestion;
    private Question question;

    public Answer(Question question, int choiceIndexInQuestion) {
        this.question = question;
        this.choiceIndexInQuestion = choiceIndexInQuestion;
    }

    public Answer(Question question, String choice) {
        this.question = question;
        this.choiceIndexInQuestion = question.getChoices().indexOf(choice);
    }
    
    public int getChoiceIndexInQuestion() { return choiceIndexInQuestion; }
    public Question getQuestion() { return question; }

    public boolean doesNotFulfillBasicCriterion(Criterion criterion) {
        return !criterion.isFulfilledBy(this) 
            && criterion.isBasicCriterion();
    }

    @Override
    public int hashCode() {
        return question.getChoices().getChoiceAt(choiceIndexInQuestion)
            .hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        Answer otherAnswer = (Answer) obj;
        return question.getId() == otherAnswer.question.getId()
            && choiceIndexInQuestion == otherAnswer.choiceIndexInQuestion;
    }

    @Override
    public String toString() {
        return String.format(
            "%s %s", 
            question.getText(), 
            question.getChoices().getChoiceAt(choiceIndexInQuestion)
        );
    }
}