package com.cnam.quiz.common.dto;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.cnam.quiz.common.enums.SubscriberStatus;
import com.cnam.quiz.server.domain.questions.Question;
import com.cnam.quiz.server.domain.user.User;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class SequenceWithQuestionsDtoSerializer extends JsonSerializer<SequenceWithQuestionsDto> {

	@Override
	public void serialize(SequenceWithQuestionsDto sequenceWithQuestionsDto, JsonGenerator jgen,
			SerializerProvider provider) throws IOException, JsonProcessingException {
		System.out.println("test");
		jgen.writeStartObject();
		jgen.writeNumberField("id", sequenceWithQuestionsDto.getId());
		jgen.writeStringField("name", sequenceWithQuestionsDto.getName());
		jgen.writeStringField("description", sequenceWithQuestionsDto.getDescription());
		jgen.writeNumberField("userId", sequenceWithQuestionsDto.getUserId());

		Map<Integer, QuestionDto> questionsDto = sequenceWithQuestionsDto.getQuestions();
		jgen.writeArrayFieldStart("questions");
		for (Map.Entry<Integer, QuestionDto> e : questionsDto.entrySet()) {
			int positionInSequence = e.getKey();
			QuestionDto questionDto = e.getValue();
			jgen.writeStartObject();
			jgen.writeNumberField("positionInSequence", positionInSequence );
			jgen.writeNumberField("id", questionDto.getId());
			jgen.writeStringField("title", questionDto.getTitle());
			jgen.writeStringField("question", questionDto.getQuestion());
			jgen.writeNumberField("points", questionDto.getPoints());
			jgen.writeNumberField("position", questionDto.getPosition());
			jgen.writeNumberField("topicId", questionDto.getTopicId());
			jgen.writeNumberField("timeToAnswer", questionDto.getTimeToAnswer());
			jgen.writeStringField("questionType", questionDto.getQuestionType().toString());
			Map<String, Boolean> answers = questionDto.getAnswers();
			jgen.writeArrayFieldStart("answers");
			for (Map.Entry<String, Boolean> f : answers.entrySet()) {
				String text = f.getKey();
				boolean isItTrue = f.getValue();
				jgen.writeStartObject();
				jgen.writeStringField("text", text);
				jgen.writeBooleanField("isItTrue", isItTrue);
				jgen.writeEndObject();
			}
			jgen.writeEndArray();
			jgen.writeEndObject();
		}
		jgen.writeEndArray();
		jgen.writeEndObject();
	}
}
