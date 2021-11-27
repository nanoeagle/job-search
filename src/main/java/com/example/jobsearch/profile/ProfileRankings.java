package com.example.jobsearch.profile;

import java.util.*;
import java.util.Map.Entry;

import com.example.jobsearch.criterion.Criteria;

public class ProfileRankings {
    private AnswerSetScoreCalculator scoreCalculator;
    private TreeMap<Profile, Integer> rankings;

    public ProfileRankings(Criteria criteria) {
        scoreCalculator = new AnswerSetScoreCalculator(criteria);
        rankings = new TreeMap<>(new ScoreBasedProfileComparator());
    }

    public void add(Profile profile) {
        if (profile != null) {
            scoreCalculator.setProfileAnswers(profile.getAnswers());
            int profileScore = scoreCalculator.calculateTotalScore();
            rankings.put(profile, profileScore);
        } else 
            throw new IllegalArgumentException("The input profile is null.");
    }

    public Integer remove(Profile profile) {
        return rankings.remove(profile);
    }

    public NavigableMap<Profile, Integer> getRankings() {
        return Collections.unmodifiableNavigableMap(rankings);
    }

    @Override
    public String toString() {
        String rankingList = "\n\t-----------------------";
        for (Entry<Profile, Integer> entry : rankings.entrySet()) 
            rankingList += "\n\t" + entry.getKey() + ": " + entry.getValue();
        rankingList += "\n\t-----------------------";
        return rankingList;
    }

    private class ScoreBasedProfileComparator implements Comparator<Profile> {
        @Override
        public int compare(Profile profileA, Profile profileB) {
            scoreCalculator.setProfileAnswers(profileA.getAnswers());
            int profile_A_Score = scoreCalculator.calculateTotalScore();
    
            scoreCalculator.setProfileAnswers(profileB.getAnswers());
            int profile_B_Score = scoreCalculator.calculateTotalScore();
            
            return profile_B_Score - profile_A_Score;
        }
    }
}