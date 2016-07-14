package com.cnam.quiz.common.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.cnam.quiz.server.domain.sessionquiz.SessionQuiz;
import com.cnam.quiz.server.domain.user.User;

public class ResultDto implements Serializable {
	private long id;
	long questionId;
	float answerTime;
	long userId;
	long sessionQuizId;	
	String question;
	private Map <String,boolean[]> answers;
	int obtainedPoints;
	Date date;
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
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public long getSessionQuizId() {
		return sessionQuizId;
	}
	public void setSessionQuizId(long sessionQuizId) {
		this.sessionQuizId = sessionQuizId;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	

	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public int getObtainedPoints() {
		return obtainedPoints;
	}
	
	public void setObtainedPoints(int obtainedPoints) {
		this.obtainedPoints = obtainedPoints;
	}
	
	public Map<String, boolean[]> getAnswers() {
		return answers;
	}
	
	public void setAnswers(Map<String, boolean[]> answers) {
		this.answers = answers;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(answerTime);
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + obtainedPoints;
		result = prime * result + ((question == null) ? 0 : question.hashCode());
		result = prime * result + (int) (questionId ^ (questionId >>> 32));
		result = prime * result + (int) (sessionQuizId ^ (sessionQuizId >>> 32));
		result = prime * result + (int) (userId ^ (userId >>> 32));
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
		ResultDto other = (ResultDto) obj;
		if (Float.floatToIntBits(answerTime) != Float.floatToIntBits(other.answerTime))
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
		if (question == null) {
			if (other.question != null)
				return false;
		} else if (!question.equals(other.question))
			return false;
		if (questionId != other.questionId)
			return false;
		if (sessionQuizId != other.sessionQuizId)
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}
	
	
	
}
