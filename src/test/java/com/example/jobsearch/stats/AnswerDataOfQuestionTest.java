package com.example.jobsearch.stats;

import static org.hamcrest.MatcherAssert.*;
import static org.junit.Assert.fail;
import static org.hamcrest.CoreMatchers.*;

import com.example.jobsearch.answer.Answer;
import com.example.jobsearch.exception.ExceptionMessages;
import com.example.jobsearch.question.*;

import java.util.*;

import org.junit.*;

public class AnswerDataOfQuestionTest {
	private final int THE_NUMBER_OF_ANSWERS = 10;
	private final int THE_NUMBER_OF_ANSWERS_YES = 7;
	private final int THE_NUMBER_OF_ANSWERS_NO = 
		THE_NUMBER_OF_ANSWERS - THE_NUMBER_OF_ANSWERS_YES;
	
	private AnswerDataOfQuestion answerData;
	private Answer answerYes;
	private Answer answerNo;
	private Answer answerForAnotherQuestion;
	
	@Before
	public void initAnswerDataAndSampleAnswers() {
		Question question = new YesNoQuestion(1, "Are you ok?");
		Answer[] answers = new Answer[THE_NUMBER_OF_ANSWERS];
		for (int i = 0; i < answers.length; i++) {
			answers[i] = new Answer(question, 
				i < THE_NUMBER_OF_ANSWERS_YES ? "Yes" : "No");
		}
		answerData = new AnswerDataOfQuestion(question);
		answerData.add(Arrays.asList(answers));
		answerYes = answers[0];
		answerNo = answers[answers.length - 1];
	}

	@Before
	public void initAnswerForAnotherQuestion() {
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
	public void gettingTheNumberOfAnswersNotForTheQuestionThrowsException() {
		answerData.getTheNumberOfAnswersEquivalentTo(answerForAnotherQuestion);
	}

	@Test
	public void gettingTheNumberOfAnswersNotForTheQuestionNotifiesMessage() {
		String expectedMes = ExceptionMessages.ILLEGAL_ANSWER_ARGUMENT.getVal();
		try {
			answerData.getTheNumberOfAnswersEquivalentTo(
				answerForAnotherQuestion);
			fail("Does not get the expected exception.");
		} catch (IllegalArgumentException e) {
			assertThat(e.getMessage(), equalTo(expectedMes));
		}
	}

	@Test
	public void copyOfDataReflectsExactlyTheNumberOfAnswersEachKind() {
		Map<Answer, Integer> numberOfAnswersEachKind = createExpectedMap();
		Map<Answer, Integer> copy = answerData.getCopy();
		assertThat(copy, equalTo(numberOfAnswersEachKind));
	}

	private Map<Answer, Integer> createExpectedMap() {
		Map<Answer, Integer> numberOfAnswersEachKind = new HashMap<>();
		numberOfAnswersEachKind.put(answerYes, THE_NUMBER_OF_ANSWERS_YES);
		numberOfAnswersEachKind.put(answerNo, THE_NUMBER_OF_ANSWERS_NO);
		return numberOfAnswersEachKind;
	}
}