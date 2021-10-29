package com.example.jobsearch.calc;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.junit.Assert.assertEquals;

import com.example.jobsearch.Profile;
import com.example.jobsearch.answer.Answer;
import com.example.jobsearch.criterion.*;
import com.example.jobsearch.questions.*;

import org.junit.*;

public class AnswerSetScoreCalculatorTest {
    private Question question;
    private Answer desirableAnswer;
    private Answer profileAnswer;
    private Profile profile;
    private Criteria criteria;
    private AnswerSetScoreCalculator scoreCalculator;
    
    @Before
    public void createQuestionsAndAnswers() {
        question = new YesNoQuestion(1, "Got bonuses?");
        desirableAnswer = new Answer(question, YesNoAnswer.YES.getIndex());
        profileAnswer = new Answer(question, YesNoAnswer.NO.getIndex());
    }

    @Before
    public void createProfileAndCriteria() {
        profile = new Profile("James Hunter");
    }
    
    @Before
    public void createCriteria() {
        criteria = new Criteria();
    }

    @Test
    public void notFulfillingBasicCriterionResultsInNegativeScore() {
        profile.getAnswers().put(profileAnswer);
        criteria.add(new Criterion(desirableAnswer, Importance.CRITICAL));
        scoreCalculator = 
            new AnswerSetScoreCalculator(profile.getAnswers(), criteria);
        assertEquals(
            BasicScore.NOT_FULLFILL_A_BASIC_CRITERION.getValue(), 
            scoreCalculator.calculateTotalScore()
        );
    }
    
    @Test
    public void completingTrivialCriterionStillGetsItsScore() {
        profile.getAnswers().put(profileAnswer);
        Criterion criterion = 
            new Criterion(desirableAnswer, Importance.TRIVIAL);
        criteria.add(criterion);
        scoreCalculator = 
            new AnswerSetScoreCalculator(profile.getAnswers(), criteria);
        assertThat(scoreCalculator.calculateTotalScore(), 
            equalTo(criterion.getScore()));
    }
}