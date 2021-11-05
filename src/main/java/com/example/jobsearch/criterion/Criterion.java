package com.example.jobsearch.criterion;

import com.example.jobsearch.answer.Answer;

public class Criterion {
    private Answer desirableAnswer;
    private Importance importance;

    public Criterion(Answer desirableAnswer, Importance importance) {
        this.desirableAnswer = desirableAnswer;
        this.importance = importance;
    }
    
    public Answer getDesirableAnswer() { return desirableAnswer; }
    public Importance getImportance() { return importance; }
    
    public void setDesirableAnswer(Answer desirableAnswer) { 
        this.desirableAnswer = desirableAnswer; 
    }

    public void setImportance(Importance importance) { 
        this.importance = importance; 
    }

    public boolean isFulfilledBy(Answer answer) {
        return importance == Importance.TRIVIAL 
            || answer.equals(desirableAnswer);
    }

    public boolean isBasicCriterion() {
        return importance == Importance.CRITICAL 
            || importance == Importance.VERYIMPORTANT;
    }
    
    public int getScore() { 
        return importance.getValue() * Magnification.FIVE_TIMES.getValue(); 
    }
}