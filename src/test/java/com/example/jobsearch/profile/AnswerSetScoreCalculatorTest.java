package com.example.jobsearch.profile;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.junit.Assert.assertEquals;

import com.example.jobsearch.answer.Answer;
import com.example.jobsearch.criterion.*;
import com.example.jobsearch.question.*;

import org.junit.*;

public class AnswerSetScoreCalculatorTest {
    private Question question1;
    private Question question2;
    private Question question3;

    private Answer profileAnswerQ1_No;
    private Answer profileAnswerQ1_Yes;
    private Answer profileAnswerQ2_No;
    private Answer profileAnswerQ2_Yes;
    private Answer profileAnswerQ3_No;
    
    private Answer desirableAnswerQ1_Yes;
    private Answer desirableAnswerQ2_Yes;
    private Answer desirableAnswerQ3_No;
    
    private Profile profile;
    private Criteria criteria;
    private AnswerSetScoreCalculator scoreCalculator;
    
    @Before
    public void createQuestionsAndAnswers() {
        question1 = new YesNoQuestion(
            1, "Do you have a solid background in web development?");
        question2 = new YesNoQuestion(
            2, "Have you ever been involved in " +
            "a web development project using Java?");
        question3 = new YesNoQuestion(3, "blabla");

        profileAnswerQ1_No = new Answer(question1, "No");
        profileAnswerQ1_Yes = new Answer(question1, "Yes");
        profileAnswerQ2_No = new Answer(question2, "No");
        profileAnswerQ2_Yes = new Answer(question2, "Yes");
        profileAnswerQ3_No = new Answer(question3, "No");

        desirableAnswerQ1_Yes = new Answer(question1, "Yes");
        desirableAnswerQ2_Yes = new Answer(question2, "Yes");
        desirableAnswerQ3_No = new Answer(question3, "No");
    }

    @Before
    public void createProfile() {
        profile = new Profile(1, "James Hunter");
    }
    
    @Before
    public void createCriteria() {
        criteria = new Criteria();
    }

    @Test
    public void notFulfillingBasicCriterionResultsInNegativeScore() {
        profile.getAnswers().put(profileAnswerQ1_No);
        profile.getAnswers().put(profileAnswerQ2_Yes);
        
        criteria.add(new Criterion(desirableAnswerQ1_Yes, Importance.CRITICAL));
        criteria.add(new Criterion(desirableAnswerQ2_Yes, Importance.INTERESTING));
        
        scoreCalculator = new AnswerSetScoreCalculator(
            profile.getAnswers(), criteria);
        assertEquals(BasicScore.NOT_FULLFILL_BASIC_CRITERION.getValue(), 
            scoreCalculator.getTotalScore());
    }
    
    @Test
    public void fillingInWithoutFulfillingTrivialCriterionStillGetsItsScore() {
        profile.getAnswers().put(profileAnswerQ1_No);
        
        Criterion trivialCriterion = new Criterion(
            desirableAnswerQ1_Yes, Importance.TRIVIAL);
        criteria.add(trivialCriterion);
        
        scoreCalculator = new AnswerSetScoreCalculator(
            profile.getAnswers(), criteria);
        assertThat(scoreCalculator.getTotalScore(), 
            equalTo(trivialCriterion.getScore()));
    }

    @Test
    public void notFulfillingAnyCriterionWhichIsNeitherTrivialNorBasicGetsScoreZero() {
        profile.getAnswers().put(profileAnswerQ1_No);
        profile.getAnswers().put(profileAnswerQ2_No);
        
        Criterion interestingCriterion = new Criterion(
            desirableAnswerQ1_Yes, Importance.INTERESTING);
        Criterion importantCriterion = new Criterion(
            desirableAnswerQ2_Yes, Importance.IMPORTANT);
        
        criteria.add(interestingCriterion);
        criteria.add(importantCriterion);
        
        scoreCalculator = new AnswerSetScoreCalculator(
            profile.getAnswers(), criteria);
        assertThat(scoreCalculator.getTotalScore(), equalTo(0));
    }

    @Test
    public void fulfillingAllCriteriaAccumulatesAllCriterionScores() {
        profile.getAnswers().put(profileAnswerQ1_Yes);
        profile.getAnswers().put(profileAnswerQ2_Yes);
        profile.getAnswers().put(profileAnswerQ3_No);

        Criterion trivialCriterion = new Criterion(
            desirableAnswerQ1_Yes, Importance.TRIVIAL);
        Criterion importantCriterion = new Criterion(
            desirableAnswerQ2_Yes, Importance.IMPORTANT);
        Criterion basicCriterion = new Criterion(
            desirableAnswerQ3_No, Importance.VERYIMPORTANT);
        
        criteria.add(trivialCriterion);
        criteria.add(importantCriterion);
        criteria.add(basicCriterion);
        
        scoreCalculator = 
            new AnswerSetScoreCalculator(profile.getAnswers(), criteria);
        int totalScore = 0;
        for (Criterion criterion : criteria) totalScore += criterion.getScore();
        assertThat(scoreCalculator.getTotalScore(), equalTo(totalScore));
    }
}