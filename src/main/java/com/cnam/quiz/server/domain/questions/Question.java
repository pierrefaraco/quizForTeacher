package com.cnam.quiz.server.domain.questions;

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
@Entity
@NamedQueries({
	@NamedQuery(name = "findAllQuestions", query="select o from Question o"),
	@NamedQuery(name = "findQuestionsByTopic", query="select o from Question o WHERE o.topic.id = :topicid "),
})



@Table(name = "questions")
public class Question extends DomainObject implements  Serializable{

	@Id
	@GeneratedValue
	@Column(name="id")
	private long id;
	@Column(name="question", nullable = false, length = 255)
	private String question;	
	@ElementCollection
	private List <String> propositions;	
	@ElementCollection
	private List <String>  answers;	
	@Column(name="points", nullable = false )
	private int points;
	@Column(name="time_to_answer", nullable = false )
	private int timeToAnswer;
	@Column(name="position", nullable = false )
	private int position;
	@Column(name="question_type", nullable = false, length = 1)
	private QuestionType questionType;	
	@ManyToOne(fetch =FetchType.LAZY)
	@JoinColumn(name ="topic_id", nullable = false)
	private Topic topic;

	public Question(String question,List<String> propositions,
			List<String> answers, int points,int timeToAnswer ,int position,QuestionType questionType, Topic topic) {
		super();
		this.question = question;
		this.propositions = propositions;
		this.answers = answers;
		this.points = points;
		this.position = position;
		this.topic = topic;
		this.timeToAnswer =timeToAnswer;
		this.questionType =questionType;
	}

	public Question(long id, String question, List<String> propositions,
			List<String> answers, int points,int timeToAnswer, int position,QuestionType questionType, Topic topic) {
		super();
		this.id = id;
		this.question = question;
		this.propositions = propositions;
		this.answers = answers;
		this.points = points;
		this.position = position;
		this.topic = topic;
		this.timeToAnswer =timeToAnswer;
		this.questionType =questionType;
	}
	
		
	public Question() {
		super();
	}

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
	
	public Topic getTopic() {
		return topic;
	}

	public void setTopic(Topic topic) {
		this.topic = topic;
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
		result = prime * result + ((answers == null) ? 0 : answers.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + points;
		result = prime * result + position;
		result = prime * result
				+ ((propositions == null) ? 0 : propositions.hashCode());
		result = prime * result
				+ ((question == null) ? 0 : question.hashCode());
		result = prime * result
				+ ((questionType == null) ? 0 : questionType.hashCode());
		result = prime * result + timeToAnswer;
		result = prime * result + ((topic == null) ? 0 : topic.hashCode());
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
		Question other = (Question) obj;
		if (answers == null) {
			if (other.answers != null)
				return false;
		} else if (!answers.equals(other.answers))
			return false;
		if (id != other.id)
			return false;
		if (points != other.points)
			return false;
		if (position != other.position)
			return false;
		if (propositions == null) {
			if (other.propositions != null)
				return false;
		} else if (!propositions.equals(other.propositions))
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
		if (topic == null) {
			if (other.topic != null)
				return false;
		} else if (!topic.equals(other.topic))
			return false;
		return true;
	}


	


}
//*/