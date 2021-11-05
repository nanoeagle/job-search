package com.example.jobsearch.profile;

import com.example.jobsearch.answer.Answer;
import com.example.jobsearch.criterion.*;

public class AnswerSetScoreCalculator {
    private AnswerContainer profileAnswers;
    private Criteria criteria;
    private int totalScore;

    public AnswerSetScoreCalculator(AnswerContainer profileAnswers, Criteria criteria) {
        this.profileAnswers = profileAnswers;
        this.criteria = criteria;
    }

    public void setProfileAnswers(AnswerContainer profileAnswers) {
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
            if (answerScore == BasicScore.NOT_FULLFILL_BASIC_CRITERION.getValue()) {
                totalScore = answerScore;
                return;
            }
            totalScore += answerScore;
        }
    }

    private int calculateAnswerScoreAgainst(Criterion criterion) {
        Answer answer = profileAnswers.getAnswerByQuestionId(
            criterion.getDesirableAnswer().getQuestion().getId());
        if (answer.doesNotFulfillBasicCriterion(criterion)) {
            return BasicScore.NOT_FULLFILL_BASIC_CRITERION.getValue();
        }
        return criterion.isFulfilledBy(answer) ? criterion.getScore() : 0;
    }
}