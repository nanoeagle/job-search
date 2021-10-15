package com.example.jobsearch.answer;

import static org.junit.Assert.assertEquals;

import com.example.jobsearch.Profile;
import com.example.jobsearch.criterion.*;
import com.example.jobsearch.questions.*;

import org.junit.*;

public class AnswerSetScoreCalculatorTest {
    private Profile profile;
    private Question question;
    private Answer desirableAnswer;
    private Criteria criteria;
    private AnswerSetScoreCalculator scoreCalculator;
    
    @Before
    public void initializeSharedObjects() {
        question = new BooleanQuestion(1, "Got bonuses?");
        Answer profileAnswer = new Answer(question, BooleanAnswer.FALSE.getValue());
        profile = new Profile("James Hunter");
        profile.getAnswers().put(profileAnswer);
        
        desirableAnswer = new Answer(question, BooleanAnswer.TRUE.getValue());
        criteria = new Criteria();
        scoreCalculator = 
            new AnswerSetScoreCalculator(profile.getAnswers(), criteria);
    }

    @Test
    public void testTotalScoreWhenNotFulfillBasicCriterion() {
        criteria.add(new Criterion(desirableAnswer, Weight.CRITICAL));
        assertEquals(
            Score.NOT_FULLFILL_A_BASIC_CRITERION.getValue(), 
            scoreCalculator.calculateTotalScore()
        );
    }

    @Test
    public void testTotalScoreWhenFulfillOneTrivialCriterion() {
        Criterion criterion = new Criterion(desirableAnswer, Weight.TRIVIAL);
        criteria.add(criterion);
        assertEquals(
            criterion.getScore(), 
            scoreCalculator.calculateTotalScore()
        );
    }
}