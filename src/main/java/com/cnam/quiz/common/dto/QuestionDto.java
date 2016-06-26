package com.cnam.quiz.common.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;














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

import com.cnam.quiz.common.enums.AccountType;
import com.cnam.quiz.common.enums.QuestionType;
import com.cnam.quiz.server.domain.DomainObject;
import com.cnam.quiz.server.domain.topic.Topic;
import com.fasterxml.jackson.annotation.JsonInclude;

public class QuestionDto extends DomainObject implements  Serializable{

	private long id;
	private String question;	
	private List <String> propositions;	
	private List <String>  answers;	
	private int points;
	private int timeToAnswer;
	private int position;
	private QuestionType questionType;	
	private long topicId;

	public QuestionDto(){}
		

	@Override
	public long getId() {
		return id;
	}

	@Override
	public void setId(long id) {
		this.id = id ;
		
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public List<String> getPropositions() {
		return propositions;
	}

	public void setPropositions(List<String> propositions) {
		this.propositions = propositions;
	}

	public List<String> getAnswers() {
		return answers;
	}

	public void setAnswers(List<String> answers) {
		this.answers = answers;
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


}
//*/