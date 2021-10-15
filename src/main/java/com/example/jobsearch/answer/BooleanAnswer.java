package com.example.jobsearch.answer;

public enum BooleanAnswer {
   FALSE(0),
   TRUE(1);

   private int value;
   private BooleanAnswer(int value) { this.value = value; }
   public int getValue() { return value; }
}