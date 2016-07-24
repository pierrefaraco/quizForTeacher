package com.cnam.quiz.common.dto;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.cnam.quiz.common.enums.QuestionType;
import com.cnam.quiz.common.enums.SubscriberStatus;
import com.cnam.quiz.server.domain.questions.Question;
import com.cnam.quiz.server.domain.user.User;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class SequenceWithQuestionsDtoDeserializer extends JsonDeserializer<SequenceWithQuestionsDto> {

	@Override
	public SequenceWithQuestionsDto deserialize(JsonParser jsonParser, DeserializationContext arg1)
			throws IOException, JsonProcessingException {
		ObjectCodec oc = jsonParser.getCodec();
		JsonNode node = oc.readTree(jsonParser);

		SequenceWithQuestionsDto sequenceWithQuestionsDto = new SequenceWithQuestionsDto();

		long id = 0;
		if (node.get("id") != null)
			id = node.get("id").asLong();
		String name = node.get("name").textValue();
		String description = node.get("description").textValue();
		long userId = node.get("userId").asLong();

		sequenceWithQuestionsDto.setId(id);
		sequenceWithQuestionsDto.setName(name);
		sequenceWithQuestionsDto.setDescription(description);
		sequenceWithQuestionsDto.setUserId(userId);

		Map<Integer, QuestionDto> questions = new HashMap<Integer, QuestionDto>();

		JsonNode questionsNode = node.get("questions");
		if (questionsNode!= null &&  questionsNode.isArray())
			for (final JsonNode  questionNode : questionsNode) {
				QuestionDto questionDto = new QuestionDto();
				long questionId = 0;
				if (node.get("id") != null)
					questionId = questionNode.get("id").asLong();
				String title =  questionNode.get("title").textValue();
				String question =  questionNode.get("question").textValue();
				int points =  questionNode.get("points").asInt();
				int timeToAnswer = questionNode.get("timeToAnswer").asInt();
				int position =  questionNode.get("position").asInt();
				long topicId =  questionNode.get("topicId").asLong();
				QuestionType questionType = QuestionType.valueOf( questionNode.get("questionType").textValue());

				questionDto.setId(questionId);
				questionDto.setTitle(title);
				questionDto.setPoints(points);
				questionDto.setQuestion(question);
				questionDto.setPosition(position);
				questionDto.setTimeToAnswer(timeToAnswer);
				questionDto.setTopicId(topicId);
				questionDto.setQuestionType(questionType);

				System.out.println("point timetoanswer =========> " + points + " " + timeToAnswer);
				int i = 0;

				Map<String, Boolean> answers = new HashMap<String, Boolean>();
				JsonNode answersNode =  questionNode.get("answers");

				if (answersNode.isArray())
					for (final JsonNode answerNode : answersNode) {
						String key = answerNode.get("text").textValue();
						Boolean value = answerNode.get("isItTrue").asBoolean();
						answers.put(key, value);
					}
				
				questionDto.setAnswers(answers);
				int positionInSequence  =  questionNode.get("positionInSequence").asInt();		
				questions.put( positionInSequence , questionDto);
			}
		sequenceWithQuestionsDto.setQuestions(questions);
		return sequenceWithQuestionsDto;
	}
}
