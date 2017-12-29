package l2k.trivia.game;

import java.util.ArrayList;

public class SampleTriviaGameBuilder extends TriviaGameBuilder {
	
	{
		setRounds(new ArrayList<TriviaRound>() {{
			add(new TriviaRoundBuilder()
				.setQuestion(new Question("Which of the following is a four letter name?"))
				.setCorrectAnswer(new Answer("Todd"))
				.addFakeAnswer(new Answer("Billy"))
				.addFakeAnswer(new Answer("Tim"))
				.addFakeAnswer(new Answer("Randee"))
			.build());
			
			add(new TriviaRoundBuilder()
				.setQuestion(new Question("Which of the following is a three letter name?"))
				.setCorrectAnswer(new Answer("Tim"))
				.addFakeAnswer(new Answer("Billy"))
				.addFakeAnswer(new Answer("Todd"))
				.addFakeAnswer(new Answer("Randee"))
			.build());
			
			add(new TriviaRoundBuilder()
				.setQuestion(new Question("Which of the following is a six letter name?"))
				.setCorrectAnswer(new Answer("Randee"))
				.addFakeAnswer(new Answer("Billy"))
				.addFakeAnswer(new Answer("Todd"))
				.addFakeAnswer(new Answer("Tim"))
			.build());
		}});
	}
	
}
