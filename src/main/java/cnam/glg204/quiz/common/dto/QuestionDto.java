package cnam.glg204.quiz.common.dto;

import cnam.glg204.quiz.common.dto.jsonserial.QuestionDtoDeserializer;
import cnam.glg204.quiz.common.dto.jsonserial.QuestionDtoSerializer;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import cnam.glg204.quiz.common.enums.AccountType;
import cnam.glg204.quiz.common.enums.QuestionType;
import cnam.glg204.quiz.server.domain.DomainObject;
import cnam.glg204.quiz.server.domain.topic.Topic;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
@JsonSerialize(using = QuestionDtoSerializer.class)
@JsonDeserialize(using = QuestionDtoDeserializer .class)
public class QuestionDto implements  Serializable{

	private long id;
	private String title;	
	private String question;	
	private Map <String,Boolean> answers;
	private int points;
	private int timeToAnswer;
	private int position;
	private QuestionType questionType;		
	
	public Map<String, Boolean> getAnswers() {
		return answers;
	}

	public void setAnswers(Map<String, Boolean> answers) {
		this.answers = answers;
	}

	private long topicId;

	public QuestionDto(){}
		

	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id ;
		
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}


	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}
	


	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}
	
	public long getTopicId() {
		return topicId;
	}

	public void setTopicId(long topicId) {
		this.topicId = topicId;
	}

	public int getTimeToAnswer() {
		return timeToAnswer;
	}

	public void setTimeToAnswer(int timeToAnswer) {
		this.timeToAnswer = timeToAnswer;
	}

	public QuestionType getQuestionType() {
		return questionType;
	}

	public void setQuestionType(QuestionType questionType) {
		this.questionType = questionType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + points;
		result = prime * result + position;
		result = prime * result + ((question == null) ? 0 : question.hashCode());
		result = prime * result + ((questionType == null) ? 0 : questionType.hashCode());
		result = prime * result + timeToAnswer;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + (int) (topicId ^ (topicId >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		QuestionDto other = (QuestionDto) obj;
		if (id != other.id)
			return false;
		if (points != other.points)
			return false;
		if (position != other.position)
			return false;
		if (question == null) {
			if (other.question != null)
				return false;
		} else if (!question.equals(other.question))
			return false;
		if (questionType != other.questionType)
			return false;
		if (timeToAnswer != other.timeToAnswer)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (topicId != other.topicId)
			return false;
		return true;
	}

}
