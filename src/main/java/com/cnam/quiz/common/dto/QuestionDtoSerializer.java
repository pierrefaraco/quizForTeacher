package com.cnam.quiz.common.dto;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.cnam.quiz.common.enums.SubscriberStatus;
import com.cnam.quiz.server.domain.user.User;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class QuestionDtoSerializer extends JsonSerializer < QuestionDto >{

	@Override
	public void serialize(QuestionDto questionDto, JsonGenerator jgen,  SerializerProvider provider)
			throws IOException, JsonProcessingException {
		   jgen.writeStartObject();
		   jgen.writeNumberField("id",questionDto.getId() );
		   jgen.writeStringField("title",questionDto.getTitle());
		   jgen.writeStringField("question",questionDto.getQuestion());
		   jgen.writeNumberField("points",questionDto.getPoints());
		   jgen.writeNumberField("position",questionDto.getPosition());
		   jgen.writeNumberField("topicId",questionDto.getTopicId());
		   jgen.writeNumberField("timeToAnswer",questionDto.getTimeToAnswer());
		   jgen.writeStringField("questionType",questionDto.getQuestionType().toString());
	
		   
		   
		   Map<String, Boolean> answers =  questionDto.getAnswers();
		   jgen.writeArrayFieldStart("answers");
		   for ( Map.Entry <String, Boolean>   e : answers.entrySet()) {
			   	String answer = e.getKey();		
				boolean isItTrue = e.getValue();	
				jgen.writeStartObject();
		    	jgen.writeStringField("proposition",answer );
		    	jgen.writeBooleanField("lastName", isItTrue);
			  			    jgen.writeEndObject();		   
		   }
		   jgen.writeEndArray();
		   jgen.writeEndObject();				   
		   }		
}


