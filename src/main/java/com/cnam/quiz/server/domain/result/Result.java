package com.cnam.quiz.server.domain.result;


import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.Session;

import com.cnam.quiz.server.domain.DomainObject;
import com.cnam.quiz.server.domain.cours.Cours;
import com.cnam.quiz.server.domain.questions.Question;
import com.cnam.quiz.server.domain.sessionquiz.SessionQuiz;
import com.cnam.quiz.server.domain.user.User;

@Entity
@NamedQueries({
	@NamedQuery(name = "findAllResults", query="select o from Result o"),
	@NamedQuery(name = "findResultsByUserQuestionAndSession",query="select o from Result o where sessionQuiz.id = :sessionid and user.id = :userid and questionId = :questionid"),
	@NamedQuery(name = "findResultsByUserAndSession", query="select o from Result o where sessionQuiz.id = :sessionid and user.id = :userid"),
	@NamedQuery(name = "findResultsBySession", query="select o from Result o where sessionQuiz.id = :sessionid"),
	@NamedQuery(name = "findResultsByQuestionAndSession",query="select o from Result o where sessionQuiz.id = :sessionid and questionId = :questionid"), 
})

@Table(name = "results")
public class Result extends DomainObject  implements  Serializable {
	@Id
	@GeneratedValue
	@Column(name="id")
	private long id;
	@Column(name="question_Id")
	long questionId;
	@Column(name="answer_time")
	float answerTime;
	@ManyToOne(fetch =FetchType.LAZY)
	@JoinColumn(name ="user_id", nullable = false)
	User user;
	@ManyToOne(fetch =FetchType.LAZY)
	@JoinColumn(name ="sessionQuiz_id", nullable = false)
	SessionQuiz sessionQuiz;
	@Column(name="question")
	String question;
	@ElementCollection
	List<String> propositions;
	@ElementCollection
	List<String> goodAnswers;
	@ElementCollection
	List<String> givenAnswers;
	@Column(name="max_points")
	int maxPoints;
	@Column(name="obtained_point")
	int obtainedPoints;
	@Column(name="date")
	Date date;
	
	
	
	public Result(long id,long questionId, int obtainedPoints,int maxPoint, float answerTime, User auditor,
			SessionQuiz sessionQuiz, String  question,List<String> propositions, List<String> givenAnswers, List<String> goodAnswers, List<String> answers, Date date) {
		super();
		this.questionId = questionId;
		this.id = id;
		this.obtainedPoints = obtainedPoints;
		this.maxPoints = maxPoint;
		this.answerTime = answerTime;
		this.user = auditor;
		this.sessionQuiz = sessionQuiz;
		this.question = question;
		this.propositions= propositions;
		this.givenAnswers = givenAnswers;
		this.goodAnswers = goodAnswers;
		this.date = date;
	}	
	

	public Result(long questionId,int obtainedPoints,int maxPoints, float answerTime, User auditor, SessionQuiz sessionQuiz,
			String question,List<String> propositions,  List<String> goodAnswers, List<String> givenAnswers, Date date) {
		super();
		this.maxPoints = maxPoints;
		this.obtainedPoints =obtainedPoints;
		this.answerTime = answerTime;
		this.user = auditor;
		this.sessionQuiz = sessionQuiz;
		this.question = question;
		this.propositions= propositions;
		this.goodAnswers = goodAnswers;
		this.givenAnswers = givenAnswers;
		this.date = date;
	}


	public Result() {
		super();
	}


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


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public SessionQuiz getSessionQuiz() {
		return sessionQuiz;
	}


	public void setSessionQuiz(SessionQuiz sessionQuiz) {
		this.sessionQuiz = sessionQuiz;
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


	public List<String> getGoodAnswers() {
		return goodAnswers;
	}


	public void setGoodAnswers(List<String> goodAnswers) {
		this.goodAnswers = goodAnswers;
	}


	public List<String> getGivenAnswers() {
		return givenAnswers;
	}


	public void setGivenAnswers(List<String> givenAnswers) {
		this.givenAnswers = givenAnswers;
	}


	public int getMaxPoints() {
		return maxPoints;
	}


	public void setMaxPoints(int maxPoints) {
		this.maxPoints = maxPoints;
	}


	public int getObtainedPoint() {
		return obtainedPoints;
	}


	public void setObtainedPoint(int obtainedPoint) {
		this.obtainedPoints = obtainedPoint;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(answerTime);
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((givenAnswers == null) ? 0 : givenAnswers.hashCode());
		result = prime * result + ((goodAnswers == null) ? 0 : goodAnswers.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + maxPoints;
		result = prime * result + obtainedPoints;
		result = prime * result + ((propositions == null) ? 0 : propositions.hashCode());
		result = prime * result + ((question == null) ? 0 : question.hashCode());
		result = prime * result + (int) (questionId ^ (questionId >>> 32));
		result = prime * result + ((sessionQuiz == null) ? 0 : sessionQuiz.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		Result other = (Result) obj;
		if (Float.floatToIntBits(answerTime) != Float.floatToIntBits(other.answerTime))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (givenAnswers == null) {
			if (other.givenAnswers != null)
				return false;
		} else if (!givenAnswers.equals(other.givenAnswers))
			return false;
		if (goodAnswers == null) {
			if (other.goodAnswers != null)
				return false;
		} else if (!goodAnswers.equals(other.goodAnswers))
			return false;
		if (id != other.id)
			return false;
		if (maxPoints != other.maxPoints)
			return false;
		if (obtainedPoints != other.obtainedPoints)
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
		if (questionId != other.questionId)
			return false;
		if (sessionQuiz == null) {
			if (other.sessionQuiz != null)
				return false;
		} else if (!sessionQuiz.equals(other.sessionQuiz))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	


	
}
