package cnam.glg204.quiz.common.dto;
import cnam.glg204.quiz.common.dto.jsonserial.SequenceWithQuestionsDtoSerializer;
import cnam.glg204.quiz.common.dto.jsonserial.SequenceWithQuestionsDtoDeserializer;
import java.io.Serializable;
import java.util.Map;

import javax.persistence.JoinColumn;

import cnam.glg204.quiz.server.domain.DomainObject;
import cnam.glg204.quiz.server.domain.questions.Question;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;


@JsonSerialize(using = SequenceWithQuestionsDtoSerializer.class)
@JsonDeserialize(using = SequenceWithQuestionsDtoDeserializer.class)
public class SequenceWithQuestionsDto extends SequenceDto implements  Serializable {

	public SequenceWithQuestionsDto(){}
	Map <Integer,QuestionDto> questions;
		
	public Map<Integer, QuestionDto> getQuestions() {
		return questions;
	}
        
	public void setQuestions(Map<Integer, QuestionDto> questions) {
		this.questions = questions;
	}
	
}
