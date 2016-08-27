package cnam.glg204.quiz.common.dto.jsonserial;
import cnam.glg204.quiz.common.dto.ResultDto;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ResultDtoDeserializer extends JsonDeserializer<ResultDto> {
	private static final ObjectMapper mapper = new ObjectMapper();

	@Override
	public ResultDto deserialize(JsonParser jsonParser, DeserializationContext arg1)
			throws IOException, JsonProcessingException {

		ObjectCodec oc = jsonParser.getCodec();
		JsonNode node = oc.readTree(jsonParser);

		ResultDto resultDto = new ResultDto ();
		long id = 0;
		float answerTime = 0;
		if (node.get("id") != null)
			id = node.get("id").asLong();
		if (node.get("answerTime") != null)
			answerTime = node.get("answerTime").floatValue();
		String title = node.get("title").textValue();
		String question = node.get("question").textValue();
		int points = node.get("points").asInt();
		long userId = node.get("userId").asLong();
		long questionId =  node.get("questionId").asLong();
		long sessionQuizId  =  node.get("sessionQuizId").asLong();
		long coursId  =  node.get("coursId").asLong();
		resultDto.setId(id);
		resultDto.setQuestionId(questionId);
		resultDto.setUserId(userId);
		resultDto.setTitle(title);
		resultDto.setQuestion(question);
		resultDto.setPoints(points);
		resultDto.setAnswerTime(answerTime);
		resultDto.setSessionQuizId(sessionQuizId);
		resultDto.setCoursId(coursId);
		int i = 0;         
		Map <String, boolean[]> answers =  new  HashMap<String, boolean[]> ();
		JsonNode answersNode = node.get("answers");

		if (answersNode .isArray()) 
			for (final JsonNode answerNode : answersNode) {
				String key = answerNode.get("text").textValue();
				boolean value = answerNode.get("isItTrue").asBoolean();
				boolean checked  = answerNode.get("checked").asBoolean();
				boolean [] r = { value , checked };
				answers.put(key,r);
			}
		
		resultDto.setAnswers(answers);
		return resultDto;
	}
	
}