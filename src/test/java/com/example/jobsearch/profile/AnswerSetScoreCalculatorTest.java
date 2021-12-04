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

	private Answer answerQ1_No;
	private Answer answerQ1_Yes;
	private Answer answerQ2_No;
	private Answer answerQ2_Yes;
	private Answer answerQ3_No;
	
	private Answer desirableAnswerQ1_Yes;
	private Answer desirableAnswerQ2_Yes;
	private Answer desirableAnswerQ3_No;
	
	private AnswerContainer answers;
	private Criteria criteria;
	
	@Before
	public void initQuestionsAndAnswers() {
		question1 = new YesNoQuestion(
			1, "Do you have a solid background in web development?");
		question2 = new YesNoQuestion(
			2, "Have you ever been involved in " +
			"a web development project using Java?");
		question3 = new YesNoQuestion(3, "blabla");

		answerQ1_No = new Answer(question1, "No");
		answerQ1_Yes = new Answer(question1, "Yes");
		answerQ2_No = new Answer(question2, "No");
		answerQ2_Yes = new Answer(question2, "Yes");
		answerQ3_No = new Answer(question3, "No");

		desirableAnswerQ1_Yes = new Answer(question1, "Yes");
		desirableAnswerQ2_Yes = new Answer(question2, "Yes");
		desirableAnswerQ3_No = new Answer(question3, "No");
	}

	@Before
	public void initAnswerContainer() {
		answers = new AnswerContainer();
	}
	
	@Before
	public void initCriteria() {
		criteria = new Criteria();
	}

	@Test
	public void notFulfillingBasicCriterionResultsInNegativeScore() {
		answers.put(new Answer[] {answerQ1_No, answerQ2_Yes});
		criteria.add(new Criterion[] {
			new Criterion(desirableAnswerQ1_Yes, Importance.CRITICAL),
			new Criterion(desirableAnswerQ2_Yes, Importance.INTERESTING)
		});
		assertEquals(BasicScore.NOT_FULLFILL_BASIC_CRITERION.getVal(), 
			createScoreCalculator().getTotalScore());
	}
	
	@Test
	public void notFulfillingTrivialCriterionStillGetsItsScore() {
		answers.put(answerQ1_No);
		Criterion trivialCriterion = new Criterion(
			desirableAnswerQ1_Yes, Importance.TRIVIAL);
		criteria.add(trivialCriterion);
		assertThat(createScoreCalculator().getTotalScore(), 
			equalTo(trivialCriterion.getScore()));
	}

	@Test
	public void notFulfillingNeitherTrivialNorBasicCriterionGetsZeroScore() {
		answers.put(new Answer[] {answerQ1_No, answerQ2_No});
		criteria.add(new Criterion[] {
			new Criterion(desirableAnswerQ1_Yes, Importance.INTERESTING),
			new Criterion(desirableAnswerQ2_Yes, Importance.IMPORTANT)
		});
		assertThat(createScoreCalculator().getTotalScore(), equalTo(0));
	}

	@Test
	public void fulfillingAllCriteriaAccumulatesAllCriterionScores() {
		answers.put(new Answer[] {
			answerQ1_Yes, answerQ2_Yes, answerQ3_No});
		criteria.add(new Criterion[] {
			new Criterion(desirableAnswerQ1_Yes, Importance.TRIVIAL),
			new Criterion(desirableAnswerQ2_Yes, Importance.IMPORTANT),
			new Criterion(desirableAnswerQ3_No, Importance.VERYIMPORTANT)
		});
		
		int expectedScore = 0;
		for (Criterion criterion : criteria) 
			expectedScore += criterion.getScore();
		assertThat(createScoreCalculator().getTotalScore(), 
			equalTo(expectedScore));
	}

	private AnswerSetScoreCalculator createScoreCalculator() {
		return new AnswerSetScoreCalculator(answers, criteria);
	}
}