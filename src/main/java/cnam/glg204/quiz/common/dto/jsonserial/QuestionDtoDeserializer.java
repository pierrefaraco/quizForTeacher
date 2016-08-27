package cnam.glg204.quiz.common.dto.jsonserial;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.springframework.context.annotation.Configuration;

import cnam.glg204.quiz.common.dto.CoursDto;
import cnam.glg204.quiz.common.dto.QuestionDto;
import cnam.glg204.quiz.common.enums.QuestionType;
import cnam.glg204.quiz.common.enums.SubscriberStatus;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class QuestionDtoDeserializer extends JsonDeserializer<QuestionDto> {
	private static final ObjectMapper mapper = new ObjectMapper();

	@Override
	public QuestionDto deserialize(JsonParser jsonParser, DeserializationContext arg1)
			throws IOException, JsonProcessingException {

		ObjectCodec oc = jsonParser.getCodec();
		JsonNode node = oc.readTree(jsonParser);

		QuestionDto questionDto = new 	QuestionDto ();
		long id = 0;
		if (node.get("id") != null)
			id = node.get("id").asLong();
		String title = node.get("title").textValue();
		String question = node.get("question").textValue();
		int points = node.get("points").asInt();
		int timeToAnswer = node.get("timeToAnswer").asInt();
		int position = node.get("position").asInt();
		long topicId = node.get("topicId").asLong();
		QuestionType questionType  = QuestionType .valueOf( node.get("questionType").textValue());

		questionDto.setId(id);
		questionDto.setTitle(title);
		questionDto.setPoints(points);
		questionDto.setQuestion(question);
		questionDto.setPosition(position);
		questionDto.setTimeToAnswer(timeToAnswer);
		questionDto.setTopicId(topicId);
		questionDto.setQuestionType(questionType);
		
		System.out.println("point timetoanswer =========> "+points +" "+timeToAnswer );
		int i = 0;
             
		Map <String, Boolean> answers =  new  HashMap<String, Boolean> ();
		JsonNode answersNode = node.get("answers");

		if (answersNode .isArray()) 
			for (final JsonNode answerNode : answersNode) {
				String key = answerNode.get("text").textValue();
				Boolean value = answerNode.get("isItTrue").asBoolean();
				answers.put(key, value);
			}
			questionDto.setAnswers(answers);
			

			System.out.println("point timetoanswer =========> "+questionDto.getPoints() +" "+questionDto.getTimeToAnswer());
			return questionDto;
	}
	
}