package com.example.jobsearch.profile;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import com.example.jobsearch.answer.Answer;
import com.example.jobsearch.criterion.*;
import com.example.jobsearch.question.*;

import org.junit.*;

public class ProfileRankingsTest {
	private Profile profile1;
	private Profile profile2;
	private Profile profile3;
	private ProfileRankings profileRankings;

	@Before
	public void init() {
		Question[] questions = createQuestions();
		Answer[] desirableAnswers = createDesirableAnswersBasedOn(questions);
		Criteria criteria = createCriteriaBasedOn(desirableAnswers);
		AnswerContainer[] profilesAnswers = 
			createProfilesAnswersUsing(questions, desirableAnswers);
		initProfilesUsing(profilesAnswers);
		initProfileRankingsBasedOn(criteria);
	}

	private Question[] createQuestions() {
		return new Question[] {
			new YesNoQuestion(
				1, "Do you have a solid background in web development?"),
			new YesNoQuestion(
				2, "Have you ever been involved in " +
				"a web development project using Java?")};
	}

	private Answer[] createDesirableAnswersBasedOn(Question[] questions) {
		return new Answer[] {
			new Answer(questions[0], "Yes"),
			new Answer(questions[1], "Yes"),
			new Answer(new YesNoQuestion(3, "blabla"), "No")};
	}

	private Criteria createCriteriaBasedOn(Answer[] desirableAnswers) {
		Criteria criteria = new Criteria();
		criteria.add(new Criterion[] {
			new Criterion(
				desirableAnswers[0], Importance.CRITICAL),
			new Criterion(
				desirableAnswers[1], Importance.IMPORTANT),
			new Criterion(
				desirableAnswers[2], Importance.TRIVIAL)
		});
		return criteria;
	}

	private void initProfilesUsing(AnswerContainer[] profilesAnswers) {
		profile1 = new Profile(1, "Anna");
		profile2 = new Profile(2, "Jason");
		profile3 = new Profile(3, "Mickey");
		profile1.setAnswers(profilesAnswers[0]);
		profile2.setAnswers(profilesAnswers[1]);
		profile3.setAnswers(profilesAnswers[2]);
	}

	private AnswerContainer[] createProfilesAnswersUsing(Question[] questions, 
	Answer[] desirableAnswers) {
		AnswerContainer profile_1_Answers = new AnswerContainer();
		profile_1_Answers.put(desirableAnswers[0], 
			desirableAnswers[1], desirableAnswers[2]);
		
		AnswerContainer profile_2_Answers = new AnswerContainer();
		profile_2_Answers.put(new Answer(questions[0], "No"), 
			desirableAnswers[1], desirableAnswers[2]);
		
		AnswerContainer profile_3_Answers = new AnswerContainer();
		profile_3_Answers.put(desirableAnswers[0], 
			new Answer(questions[1], "No"), desirableAnswers[2]);
		
		return new AnswerContainer[] {profile_1_Answers, 
			profile_2_Answers, profile_3_Answers};
	}

	private void initProfileRankingsBasedOn(Criteria criteria) {
		profileRankings = new ProfileRankings(criteria);
		profileRankings.add(profile1, profile2, profile3);
	}

	@Test
	public void gettingProfileRankingsResultsInDescOrderOfScores() {
		Profile[] expectedOrder = {profile1, profile3, profile2};
		Profile[] actualOrder = profileRankings.getRankings()
			.navigableKeySet().toArray(new Profile[expectedOrder.length]);
		assertThat(actualOrder, equalTo(expectedOrder));
	}
}