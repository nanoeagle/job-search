package com.example.jobsearch.profile;

import java.util.*;

import com.example.jobsearch.answer.Answer;
import com.example.jobsearch.question.*;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.junit.Assert.assertTrue;

import org.junit.*;

public class AnswerContainerTest {
	private TreeSet<Answer> sampleAnswers;
	private AnswerContainer answerContainer;

	@Before
	public void initSampleAnswers() {
		sampleAnswers = new TreeSet<>(new TemporaryAnswerComparator());
		composeSampleAnswers(new Answer[] {
			new Answer(
				new YesNoQuestion(1, "Are you ok?"), "Yes"),
			new Answer(
				new YesNoQuestion(2, "Have you graduated yet?"), "No"),
			new Answer(
				new NormalQuestion(
					3, "What is your favorite means of transport?", 
					new ChoiceContainer(new String[] {"Bus", "Car", "Bike"})
				), "Bus")
		});
	}

	private void composeSampleAnswers(Answer[] answers) {
		for (Answer answer : answers) sampleAnswers.add(answer);
	}

	@Before
	public void initAnswerContainer() {
		answerContainer = new AnswerContainer();
	}

	@Test
	public void assertThatTheFilterMethodWorksCorrectly() {
		composeAnswerContainerUsingSampleAnswers();
		Set<Answer> answersForYesNoQuestions = answerContainer.filterBy(
			a -> a.getQuestion().getClass() == YesNoQuestion.class);
		Set<Answer> answersNotForYesNoQuestions = answerContainer.filterBy(
			a -> a.getQuestion().getClass() != YesNoQuestion.class);
		
		TreeSet<Answer> allAnswers = 
			new TreeSet<>(new TemporaryAnswerComparator());
		allAnswers.addAll(answersForYesNoQuestions);
		allAnswers.addAll(answersNotForYesNoQuestions);

		assertThat(allAnswers, equalTo(sampleAnswers));
	}

	private void composeAnswerContainerUsingSampleAnswers() {
		for (Answer answer : sampleAnswers) answerContainer.put(answer);
	}

	@Test
	public void assertThePerformanceOfTheFilterMethodIsAcceptable() {
		composeAnswerContainerUsingCustomAnswers();
		int acceptableTimeInMs = 1000;
		int numberOfTestingTimes = 1000;
		Runnable task = createTask();
		int elapsedTimeInMs = testPerformance(numberOfTestingTimes, task);
		assertTrue(elapsedTimeInMs <= acceptableTimeInMs);
	}

	private void composeAnswerContainerUsingCustomAnswers() {
		int dataSize = 5000;
		for (int i = 0; i < dataSize; i++)
			answerContainer.put(new Answer(
				new YesNoQuestion(i, "Are you ok? " + i), "Yes"));
		
		answerContainer.put(
			new Answer(
				new NormalQuestion(
					dataSize, "What is your favorite means of transport?", 
					new ChoiceContainer(new String[] {"Bus", "Car", "Bike"})
				), "Bus"));
	}

	private Runnable createTask() {
		return () -> answerContainer.filterBy(a -> 
			a.getQuestion().getClass() != YesNoQuestion.class);
	}

	private int testPerformance(int numberOfTimes, Runnable task) {
		long start = System.nanoTime();
		for (int i = 0; i < numberOfTimes; i++) task.run();
		long stop = System.nanoTime();
		return (int) (stop - start) / 1000000;
	}

	private static final class TemporaryAnswerComparator 
	implements Comparator<Answer> {
		@Override
		public int compare(Answer a1, Answer a2) {
			int sub = a1.getQuestion().getId() - a2.getQuestion().getId();
			return (sub != 0) ? sub : 
				a1.getChoiceIndexInQuestion() - a2.getChoiceIndexInQuestion();
		}
	}
}