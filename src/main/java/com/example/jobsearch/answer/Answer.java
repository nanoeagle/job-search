package com.example.jobsearch.answer;

import com.example.jobsearch.exception.ExceptionMessages;
import com.example.jobsearch.question.Question;

public class Answer {
    private Question question;
    private int choiceIndexInQuestion;

    public Answer(Question question, int choiceIndex) {
        this.question = question;
        if (question.getChoices().isChoiceIndexWithinBounds(choiceIndex)) {
            choiceIndexInQuestion = choiceIndex;
        } else throw new IllegalArgumentException(
            ExceptionMessages.ILLEGAL_CHOICE_INDEX_ARGUMENT.getVal()); 
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

    public boolean isFor(Question question) {
        return this.question.getId() == question.getId();
    }

    @Override
    public int hashCode() {
        return question.getId();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
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
}