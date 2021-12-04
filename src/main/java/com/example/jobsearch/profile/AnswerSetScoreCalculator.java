package com.example.jobsearch.profile;

import com.example.jobsearch.answer.Answer;
import com.example.jobsearch.criterion.*;

public class AnswerSetScoreCalculator {
    private AnswerContainer profileAnswers;
    private Criteria criteria;

    public AnswerSetScoreCalculator(AnswerContainer profileAnswers, 
    Criteria criteria) {
        this.profileAnswers = profileAnswers;
        this.criteria = criteria;
    }

    public int getTotalScore() {
        int totalScore = 0;
        for (Criterion criterion : criteria) {
            int answerScore = calculateAnswerScoreAgainst(criterion);
            if (answerScore == BasicScore.NOT_FULLFILL_BASIC_CRITERION.getVal())
                return answerScore;
            totalScore += answerScore;
        }
        return totalScore;
    }

    private int calculateAnswerScoreAgainst(Criterion criterion) {
        Answer answer = profileAnswers.getAnswerByCriterion(criterion);
        boolean answerFulfillsCriterion = criterion.isFulfilledBy(answer);
        if (!answerFulfillsCriterion && criterion.isBasicCriterion())
            return BasicScore.NOT_FULLFILL_BASIC_CRITERION.getVal();
        return answerFulfillsCriterion ? criterion.getScore() : 0;
    }
}