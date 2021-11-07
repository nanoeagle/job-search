package com.example.jobsearch.exception;

public enum ExceptionMessages {
    ILLEGAL_CHOICE_INDEX_ARGUMENT(
        "The index of the choice is out of bounds."),
    ILLEGAL_ANSWER_ARGUMENT(
        "The specified answer is not for the question whose this data.");

    private String value;
    private ExceptionMessages(String value) { this.value = value; }
    public String getValue() { return value; }
}