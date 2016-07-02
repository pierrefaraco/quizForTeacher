package com.cnam.quiz.common.dto;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.cnam.quiz.server.domain.sessionquiz.SessionQuiz;
import com.cnam.quiz.server.domain.user.User;

public class ResultDto {
	private long id;
	long questionId;
	float answerTime;
	long userId;
	long sessionQuizId;	
	String question;
	List<String> propositions;
	List<String> goodAnswers;
	List<String> givenAnswers;	
	int maxPoints;
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
	public List<String> getGoodAnswers() {
		return goodAnswers;
	}
	public void setGoodAnswers(List<String> goodAnswers) {
		this.goodAnswers = goodAnswers;
	}
	public List<String> getGivenAnswers() {
		return givenAnswers;
	}
	public void setGivenAnswers(List<String> answers) {
		this.givenAnswers = answers;
	}

	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public List<String> getPropositions() {
		return propositions;
	}
	
	public void setPropositions(List<String> propositions) {
		this.propositions = propositions;
	}
	
	public int getMaxPoints() {
		return maxPoints;
	}
	
	public void setMaxPoints(int maxPoints) {
		this.maxPoints = maxPoints;
	}
	
	public int getObtainedPoints() {
		return obtainedPoints;
	}
	
	public void setObtainedPoints(int obtainedPoints) {
		this.obtainedPoints = obtainedPoints;
	}
	
}
