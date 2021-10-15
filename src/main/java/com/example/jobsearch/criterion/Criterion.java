package com.example.jobsearch.criterion;

import com.example.jobsearch.Scoreable;
import com.example.jobsearch.answer.Answer;

public class Criterion implements Scoreable {
    private Answer desirableAnswer;
    private Weight weight;

    public Criterion(Answer desirableAnswer, Weight weight) {
        this.desirableAnswer = desirableAnswer;
        this.weight = weight;
    }
    
    @Override
    public int getScore() { return weight.getValue(); }
    public Answer getDesirableAnswer() { return desirableAnswer; }
    public Weight getWeight() { return weight; }
    
    public void setDesirableAnswer(Answer desirableAnswer) { this.desirableAnswer = desirableAnswer; }
    public void setWeight(Weight weight) { this.weight = weight; }

    public boolean isFulfilledBy(Answer answer) {
        return weight == Weight.TRIVIAL 
            || answer.matches(desirableAnswer);
    }

    public boolean isBasicCriterion() {
        return weight == Weight.CRITICAL 
            || weight == Weight.VERYIMPORTANT;
    }
}