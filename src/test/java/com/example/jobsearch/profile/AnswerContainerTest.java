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
    public void createAnswerContainer() {
        sampleAnswers = new TreeSet<>();
        sampleAnswers.add(new Answer(
            new YesNoQuestion(1, "Are you ok?"), "Yes"));
        sampleAnswers.add(new Answer(
            new YesNoQuestion(2, "Have you graduated yet?"), "No"));
        sampleAnswers.add(new Answer(
            new NormalQuestion(
                3, "What is your favorite means of transport?", 
                new ChoiceContainer(new String[] {"Bus", "Car", "Bike"})
            ), "Bus"));

        answerContainer = new AnswerContainer();
        for (Answer answer : sampleAnswers) answerContainer.put(answer);   
    }
    
    @Test
    public void assertThatTheFilterMethodWorksCorrectly() {
        Set<Answer> answersForYesNoQuestions = answerContainer.filterBy(
            a -> a.getQuestion().getClass() == YesNoQuestion.class);
        Set<Answer> answersNotForYesNoQuestions = answerContainer.filterBy(
            a -> a.getQuestion().getClass() != YesNoQuestion.class);
        
        TreeSet<Answer> allAnswers = new TreeSet<>();
        allAnswers.addAll(answersForYesNoQuestions);
        allAnswers.addAll(answersNotForYesNoQuestions);
        
        assertThat(allAnswers, equalTo(sampleAnswers));
    }

    @Test
    public void assertThatTheFilterMethodPerformanceIsAcceptable() {
        answerContainer.clearAll();
        
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

        int numberOfTestingTimes = 1000;
        int elapsedTimeInMs = testPerformance(numberOfTestingTimes,
            () -> answerContainer.filterBy(
                a -> a.getQuestion().getClass() != YesNoQuestion.class));
        int acceptableTimeInMs = 1000;
        
        assertTrue(elapsedTimeInMs <= acceptableTimeInMs);
    }

    private int testPerformance(int numberOfTimes, Runnable funcToTest) {
        long start = System.nanoTime();
        for (int i = 0; i < numberOfTimes; i++) funcToTest.run();
        long stop = System.nanoTime();
        return (int) (stop - start) / 1000000;
    }
}