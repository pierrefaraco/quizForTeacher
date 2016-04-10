package com.pfaraco.quiz.server.domain.result;


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

import com.pfaraco.quiz.server.domain.DomainObject;
import com.pfaraco.quiz.server.domain.cours.Cours;
import com.pfaraco.quiz.server.domain.questions.Question;
import com.pfaraco.quiz.server.domain.sessionquiz.SessionQuiz;
import com.pfaraco.quiz.server.domain.user.User;

@Entity
@NamedQueries({
	@NamedQuery(name = "findAllResults", query="select o from Result o"),
//	@NamedQuery(name = "findResultsByUserForACours", query="select o from Result o WHERE o.user.id = :userid and o.session.cours.id = :coursid"),
//	@NamedQuery(name = "findResultsByCours", query="select o from Result o WHERE o.session.cours.id  = :coursid"),
//	@NamedQuery(name = "findResultsByUserForASession", query="select o from Result o WHERE o.user.id = :userid and o.session.id = :sessionid"),
//	@NamedQuery(name = "findResultsByUser", query="select o from Result o WHERE o.session.id  = :sessionid"),
})

@Table(name = "results")
public class Result extends DomainObject  implements  Serializable {
	@Id
	@GeneratedValue
	@Column(name="id")
	private long id;
	@Column(name="point")
	int point;
	@Column(name="answer_time")
	float answerTime;
	@ManyToOne(fetch =FetchType.LAZY)
	@JoinColumn(name ="user_id", nullable = false)
	User auditor;
	@ManyToOne(fetch =FetchType.LAZY)
	@JoinColumn(name ="cours_id", nullable = false)
	SessionQuiz sessionQuiz;
	@ManyToOne(fetch =FetchType.LAZY)
	@JoinColumn(name ="question_id", nullable = false)
	Question question;
	@ElementCollection
	List<String> answers;
	@Column(name="date")
	Date date;
	
	
	
	public Result(long id, int point, float answerTime, User auditor,
			SessionQuiz sessionQuiz, Question question, List<String> answer, Date date) {
		super();
		this.id = id;
		this.point = point;
		this.answerTime = answerTime;
		this.auditor = auditor;
		this.sessionQuiz = sessionQuiz;
		this.question = question;
		this.answers = answer;
		this.date = date;
	}
	
	
	
	public Result(int point, float answerTime, User auditor, SessionQuiz sessionQuiz,
			Question question, List<String> answer, Date date) {
		super();
		this.point = point;
		this.answerTime = answerTime;
		this.auditor = auditor;
		this.sessionQuiz = sessionQuiz;
		this.question = question;
		this.answers = answer;
		this.date = date;
	}



	public Result() {
		super();
	}


	@Override
	public long getId() {
		return id;
	}
	
	@Override
	public void setId(long id) {
		this.id = id;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	public float getAnswerTime() {
		return answerTime;
	}
	public void setAnswerTime(float answerTime) {
		this.answerTime = answerTime;
	}
	public User getAuditor() {
		return auditor;
	}
	public void setAuditor(User auditor) {
		this.auditor = auditor;
	}

	public Question getQuestion() {
		return question;
	}
	public void setQuestion(Question question) {
		this.question = question;
	}
	public List<String> getAnswer() {
		return answers;
	}
	public void setAnswer(List<String> answer) {
		this.answers = answer;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}


	public SessionQuiz getSession() {
		return sessionQuiz;
	}


	public void setSession(SessionQuiz session) {
		this.sessionQuiz = session;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((answers == null) ? 0 : answers.hashCode());
		result = prime * result + Float.floatToIntBits(answerTime);
		result = prime * result + ((auditor == null) ? 0 : auditor.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + point;
		result = prime * result
				+ ((question == null) ? 0 : question.hashCode());
		result = prime * result + ((sessionQuiz == null) ? 0 : sessionQuiz.hashCode());
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
		if (answers == null) {
			if (other.answers != null)
				return false;
		} else if (!answers.equals(other.answers))
			return false;
		if (Float.floatToIntBits(answerTime) != Float
				.floatToIntBits(other.answerTime))
			return false;
		if (auditor == null) {
			if (other.auditor != null)
				return false;
		} else if (!auditor.equals(other.auditor))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (id != other.id)
			return false;
		if (point != other.point)
			return false;
		if (question == null) {
			if (other.question != null)
				return false;
		} else if (!question.equals(other.question))
			return false;
		if (sessionQuiz == null) {
			if (other.sessionQuiz != null)
				return false;
		} else if (!sessionQuiz.equals(other.sessionQuiz))
			return false;
		return true;
	}
	
	
	
}
