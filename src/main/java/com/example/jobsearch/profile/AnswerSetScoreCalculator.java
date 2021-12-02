package com.example.jobsearch.profile;

import com.example.jobsearch.answer.Answer;
import com.example.jobsearch.criterion.*;

public class AnswerSetScoreCalculator {
    private AnswerContainer profileAnswers;
    private Criteria criteria;
    private int totalScore;

    public AnswerSetScoreCalculator(AnswerContainer profileAnswers, 
    Criteria criteria) {
        this.profileAnswers = profileAnswers;
        this.criteria = criteria;
        calculateTotalScoreAgainstCriteria();
    }

    private void calculateTotalScoreAgainstCriteria() {
        for (Criterion criterion : criteria) {
            int answerScore = calculateAnswerScoreAgainst(criterion);
            if (answerScore == BasicScore.NOT_FULLFILL_BASIC_CRITERION.getValue()) {
                totalScore = answerScore;
                return;
            }
            totalScore += answerScore;
        }
    }

    private int calculateAnswerScoreAgainst(Criterion criterion) {
        Answer answer = profileAnswers.getAnswerByCriterion(criterion);
        boolean answerFulfillsCriterion = criterion.isFulfilledBy(answer);
        if (!answerFulfillsCriterion && criterion.isBasicCriterion())
            return BasicScore.NOT_FULLFILL_BASIC_CRITERION.getValue();
        return answerFulfillsCriterion ? criterion.getScore() : 0;
    }

    public int getTotalScore() {
        return totalScore;
    }
}