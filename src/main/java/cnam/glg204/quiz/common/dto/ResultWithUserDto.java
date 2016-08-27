package cnam.glg204.quiz.common.dto;

import cnam.glg204.quiz.common.dto.jsonserial.ResultDtoDeserializer;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import cnam.glg204.quiz.server.domain.sessionquiz.SessionQuiz;
import cnam.glg204.quiz.server.domain.user.User;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
@JsonDeserialize(using = ResultDtoDeserializer .class)
public class ResultDtoForStatistic implements Serializable {
	private long id;
	long questionId;
	float answerTime;
	UserDto userDto;
	long sessionQuizId;	
	long coursId;	
	String title;
	String question;
	private Map <String,boolean[]> answers;
	int obtainedPoints;
	int points;
	String  date;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getQuestionId() {
		return questionId;
	}
	public void setQuestionId(long questionId) {
		this.questionId = questionId;
	}
	public float getAnswerTime() {
		return answerTime;
	}
	public void setAnswerTime(float answerTime) {
		this.answerTime = answerTime;
	}
	
	public long getSessionQuizId() {
		return sessionQuizId;
	}
	public void setSessionQuizId(long sessionQuizId) {
		this.sessionQuizId = sessionQuizId;
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
	public Map<String, boolean[]> getAnswers() {
		return answers;
	}
	public void setAnswers(Map<String, boolean[]> answers) {
		this.answers = answers;
	}
	public int getObtainedPoints() {
		return obtainedPoints;
	}
	public void setObtainedPoints(int obtainedPoints) {
		this.obtainedPoints = obtainedPoints;
	}
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}	
		
	public long getCoursId() {
		return coursId;
	}
		
	public void setCoursId(long coursId) {
		this.coursId = coursId;
	}
	
	public UserDto getUserDto() {
		return userDto;
	}
	
	public void setUserDto(UserDto userDto) {
		this.userDto = userDto;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(answerTime);
		result = prime * result + (int) (coursId ^ (coursId >>> 32));
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + obtainedPoints;
		result = prime * result + points;
		result = prime * result + ((question == null) ? 0 : question.hashCode());
		result = prime * result + (int) (questionId ^ (questionId >>> 32));
		result = prime * result + (int) (sessionQuizId ^ (sessionQuizId >>> 32));
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + ((userDto == null) ? 0 : userDto.hashCode());
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
		ResultDtoForStatistic other = (ResultDtoForStatistic) obj;
		if (Float.floatToIntBits(answerTime) != Float.floatToIntBits(other.answerTime))
			return false;
		if (coursId != other.coursId)
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (id != other.id)
			return false;
		if (obtainedPoints != other.obtainedPoints)
			return false;
		if (points != other.points)
			return false;
		if (question == null) {
			if (other.question != null)
				return false;
		} else if (!question.equals(other.question))
			return false;
		if (questionId != other.questionId)
			return false;
		if (sessionQuizId != other.sessionQuizId)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (userDto == null) {
			if (other.userDto != null)
				return false;
		} else if (!userDto.equals(other.userDto))
			return false;
		return true;
	}

	
	
	

	
	
	
	
}
