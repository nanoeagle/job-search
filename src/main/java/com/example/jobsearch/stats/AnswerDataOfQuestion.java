package com.example.jobsearch.stats;

import java.util.*;

import com.example.jobsearch.answer.Answer;
import com.example.jobsearch.exception.ExceptionMessages;
import com.example.jobsearch.question.Question;

public class AnswerDataOfQuestion {
    private Question question;
    private AnswerData data;

    public AnswerDataOfQuestion(Question question) {
        this.question = question;
        data = new AnswerData();
    }

    public Map<Answer, Integer> getCopy() {
        return data.getCopy();
    }

    public void add(List<Answer> answers) {
        answers.stream().filter(answer -> answer.isFor(question))
            .forEach(data::add);
    }

    public int getTheNumberOfAnswersEquivalentTo(Answer answer) 
    throws IllegalArgumentException {
        if (answer.isFor(question)) 
            return data.getTheNumberOfAnswersEquivalentTo(answer);
        throw new IllegalArgumentException(
            ExceptionMessages.ILLEGAL_ANSWER_ARGUMENT.getVal());
    }

    private static final class AnswerData {
        private Map<Answer, Integer> numberOfAnswersPerChoice;
    
        public AnswerData() {
            numberOfAnswersPerChoice = new HashMap<>();
        }
    
        public Map<Answer, Integer> getCopy() {
            return Map.copyOf(numberOfAnswersPerChoice);
        }
    
        public int getTheNumberOfAnswersEquivalentTo(Answer answer) {
            Integer theNumber = numberOfAnswersPerChoice.get(answer);
            return theNumber == null ? 0 : theNumber;
        }
        
        public void add(Answer answer) {
            if (numberOfAnswersPerChoice.containsKey(answer)) {
                increaseTheNumberOfAnswersEquivalentTo(answer);
            } else {
                putNewAnswerData(answer);
            }
        }
    
        private void increaseTheNumberOfAnswersEquivalentTo(Answer answer) {
            int oldNumber = numberOfAnswersPerChoice.get(answer);
            numberOfAnswersPerChoice.put(answer, oldNumber + 1);
        }
    
        private void putNewAnswerData(Answer newAnswer) {
            numberOfAnswersPerChoice.put(newAnswer, 1);
        }
    }
}