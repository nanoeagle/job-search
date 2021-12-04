package com.example.jobsearch.profile;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import com.example.jobsearch.answer.Answer;
import com.example.jobsearch.criterion.*;
import com.example.jobsearch.question.*;

import org.junit.*;

public class ProfileRankingsTest {
	private Criteria criteria;
	
	private Profile profile1;
	private Profile profile2;
	private Profile profile3;

	@Before
	public void init() {
		Question question1 = new YesNoQuestion(
			1, "Do you have a solid background in web development?");
		Question question2 = new YesNoQuestion(
			2, "Have you ever been involved in " +
			"a web development project using Java?");
		
		Answer desirableAnswerQ1_Yes = new Answer(question1, "Yes");
		Answer desirableAnswerQ2_Yes = new Answer(question2, "Yes");
		Answer desirableAnswerQ3_No = new Answer(
			new YesNoQuestion(3, "blabla"), "No");
		
		criteria = new Criteria();
		criteria.add(new Criterion[] {
			new Criterion(
				desirableAnswerQ1_Yes, Importance.CRITICAL),
			new Criterion(
				desirableAnswerQ2_Yes, Importance.IMPORTANT),
			new Criterion(
				desirableAnswerQ3_No, Importance.TRIVIAL)
		});

		AnswerContainer profile_1_Answers = new AnswerContainer();
		profile_1_Answers.put(desirableAnswerQ1_Yes, 
			desirableAnswerQ2_Yes, desirableAnswerQ3_No);
		
		AnswerContainer profile_2_Answers = new AnswerContainer();
		profile_2_Answers.put(new Answer(question1, "No"), 
			desirableAnswerQ2_Yes, desirableAnswerQ3_No);
		
		AnswerContainer profile_3_Answers = new AnswerContainer();
		profile_3_Answers.put(desirableAnswerQ1_Yes, 
			new Answer(question2, "No"), desirableAnswerQ3_No);

		profile1 = new Profile(1, "Anna");
		profile2 = new Profile(2, "Jason");
		profile3 = new Profile(3, "Mickey");
		profile1.setAnswers(profile_1_Answers);
		profile2.setAnswers(profile_2_Answers);
		profile3.setAnswers(profile_3_Answers);
	}

	@Test
	public void gettingProfileRankingsResultsInDescScoreOrder() {
		ProfileRankings profileRankings = new ProfileRankings(criteria);
		profileRankings.add(profile1, profile2, profile3);
		
		Profile[] expectedOrder = {profile1, profile3, profile2};
		Profile[] actualOrder = new Profile[expectedOrder.length];
		actualOrder = profileRankings.getRankings()
			.navigableKeySet().toArray(actualOrder);
		assertThat(actualOrder, equalTo(expectedOrder));
	}
}