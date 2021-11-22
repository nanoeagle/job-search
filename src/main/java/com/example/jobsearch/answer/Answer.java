package com.example.jobsearch.answer;

import com.example.jobsearch.criterion.Criterion;
import com.example.jobsearch.exception.ExceptionMessages;
import com.example.jobsearch.question.Question;

public class Answer implements Comparable<Answer> {
    private Question question;
    private int choiceIndexInQuestion;

    public Answer(Question question, int choiceIndex) {
        this.question = question;
        if (choiceIndexIsWithinBounds(choiceIndex)) {
            choiceIndexInQuestion = choiceIndex;
        } else throw new IllegalArgumentException(
            ExceptionMessages.ILLEGAL_CHOICE_INDEX_ARGUMENT
                .getValue()); 
    }

    private boolean choiceIndexIsWithinBounds(int index) {
        if (index >= 0 && index < question.getChoices().size())
            return true;
        return false;
    }

    public Answer(Question question, String choice) {
        this(question, question.getChoices().indexOf(choice));
    }
    
    public int getChoiceIndexInQuestion() { 
        return choiceIndexInQuestion; 
    }

    public Question getQuestion() { 
        return question; 
    }

    public boolean doesNotFulfillBasicCriterion(Criterion criterion) {
        return !criterion.isFulfilledBy(this) 
            && criterion.isBasicCriterion();
    }

    @Override
    public int hashCode() {
        return question.getText().hashCode() - question.getChoices()
            .getChoiceAt(choiceIndexInQuestion).hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        Answer anotherAnswer = (Answer) obj;
        return question.getId() == anotherAnswer.question.getId() && 
            choiceIndexInQuestion == anotherAnswer.choiceIndexInQuestion;
    }

    @Override
    public String toString() {
        return String.format(
            "%s %s", 
            question.getText(), 
            question.getChoices().getChoiceAt(choiceIndexInQuestion)
        );
    }

    @Override
    public int compareTo(Answer anotherAnswer) {
        int sub = question.getId() - anotherAnswer.question.getId();
        return (sub != 0) ? sub : 
            choiceIndexInQuestion - anotherAnswer.choiceIndexInQuestion;
    }
}