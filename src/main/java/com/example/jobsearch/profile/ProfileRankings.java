package com.example.jobsearch.profile;

import java.util.*;
import java.util.Map.Entry;

import com.example.jobsearch.criterion.Criteria;

public class ProfileRankings {
    private Criteria criteria;
    private TreeMap<Profile, Integer> rankings;

    public ProfileRankings(Criteria criteria) {
        this.criteria = criteria;
        rankings = new TreeMap<>(new ScoreBasedProfileComparator());
    }

    public void add(Profile profile) {
        if (profile != null) {
            int profileScore = new AnswerSetScoreCalculator(
                profile.getAnswers(), criteria).getTotalScore();
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
            int profile_A_Score = new AnswerSetScoreCalculator(
                profileA.getAnswers(), criteria).getTotalScore();
            int profile_B_Score = new AnswerSetScoreCalculator(
                profileB.getAnswers(), criteria).getTotalScore();
            return profile_B_Score - profile_A_Score;
        }
    }
}