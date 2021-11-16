package com.example.jobsearch.stats;

import static org.hamcrest.MatcherAssert.*;
import static org.junit.Assert.fail;
import static org.hamcrest.CoreMatchers.*;

import com.example.jobsearch.answer.Answer;
import com.example.jobsearch.exception.ExceptionMessages;
import com.example.jobsearch.question.*;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.*;

public class AnswerDataOfQuestionTest {
    private final int THE_NUMBER_OF_ANSWERS = 10;
    private final int THE_NUMBER_OF_ANSWERS_YES = 7;
    private final int THE_NUMBER_OF_ANSWERS_NO = 
        THE_NUMBER_OF_ANSWERS - THE_NUMBER_OF_ANSWERS_YES;
    
    private Question question;
    private Answer[] answers;
    private Answer answerYes;
    private Answer answerNo;
    private Answer answerForAnotherQuestion;
    private AnswerDataOfQuestion answerData;
    
    @Before
    public void createQuestionAndItsAnswerData() {
        question = new YesNoQuestion(1, "Are you ok?");
        answers = new Answer[THE_NUMBER_OF_ANSWERS];
        for (int i = 0; i < answers.length; i++) {
            answers[i] = new Answer(question, 
                i < THE_NUMBER_OF_ANSWERS_YES ? "Yes" : "No");
        }
        answerData = new AnswerDataOfQuestion(question.getId());
        answerData.add(Arrays.asList(answers));
        answerYes = answers[0];
        answerNo = answers[answers.length - 1];
    }

    @Before
    public void createAnswerForAnotherQuestion() {
        answerForAnotherQuestion = 
            new Answer(new YesNoQuestion(2, "blabla"), "Yes");
    }

    @Test
    public void theNumberOfAnswersYesEqualsThePredefinedValue() {
        assertThat(
            answerData.getTheNumberOfAnswersEquivalentTo(answerYes), 
            equalTo(THE_NUMBER_OF_ANSWERS_YES));
    }

    @Test(expected = IllegalArgumentException.class)
    public void gettingTheNumberOfAnswersNotForTheQuestionThrowsExpectedException() {
        answerData.getTheNumberOfAnswersEquivalentTo(answerForAnotherQuestion);
    }

    @Test
    public void gettingTheNumberOfAnswersNotForTheQuestionNotifiesExpectedMessage() {
        String expectedMes = ExceptionMessages.ILLEGAL_ANSWER_ARGUMENT.getValue();
        try {
            answerData.getTheNumberOfAnswersEquivalentTo(
                new Answer(new YesNoQuestion(2, "blabla"), "Yes"));
            fail("Does not get the expected exception.");
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage(), equalTo(expectedMes));
        }
    }

    @Test
    public void dataMapReflectsExactlyTheNumberOfAnswersEachKindAsPredefinedValues() {
        Map<Answer, AtomicInteger> dataMap = answerData.getCopyOfDataMap();
        assertThat(dataMap.get(answerYes).get(), equalTo(THE_NUMBER_OF_ANSWERS_YES));
        assertThat(dataMap.get(answerNo).get(), equalTo(THE_NUMBER_OF_ANSWERS_NO));
    }
}