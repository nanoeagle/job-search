package com.example.jobsearch.answer;

import com.example.jobsearch.collectionwrappers.AnswerMapWrapper;
import com.example.jobsearch.criterion.Criteria;
import com.example.jobsearch.criterion.Criterion;

public class AnswerSetScoreCalculator {
    private AnswerMapWrapper profileAnswers;
    private Criteria criteria;
    private int totalScore;

    public AnswerSetScoreCalculator(AnswerMapWrapper profileAnswers, Criteria criteria) {
        this.profileAnswers = profileAnswers;
        this.criteria = criteria;
    }

    public void setProfileAnswers(AnswerMapWrapper profileAnswers) {
        this.profileAnswers = profileAnswers;
    }

    public void setCriteria(Criteria criteria) {
        this.criteria = criteria;
    }

    public int calculateTotalScore() {
        totalScore = 0;
        calculateScoreAgainstCriteria();
        return totalScore;
    }

    private void calculateScoreAgainstCriteria() {
        for (Criterion criterion : criteria) {
            int answerScore = calculateAnswerScoreAgainst(criterion);
            if (answerScore == -1) {
                totalScore = -1;
                return;
            }
            totalScore += answerScore;
        }
    }

    private int calculateAnswerScoreAgainst(Criterion criterion) {
        Answer answer = profileAnswers.getByQuestionText(
            criterion.getDesirableAnswer().getQuestionText());
        if (answer.doesNotFulfillBasicCriterion(criterion)) {
            return Score.NOT_FULLFILL_A_BASIC_CRITERION.getValue();
        }
        return criterion.isFulfilledBy(answer) ? 
            criterion.getScore() : 
            Score.NOT_FULLFILL_A_NONBASIC_CRITERION.getValue();
    }
}