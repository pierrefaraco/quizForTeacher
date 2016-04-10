package com.pfaraco.quiz.server.domain.sessionquiz;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.pfaraco.quiz.server.domain.DomainObject;
import com.pfaraco.quiz.server.domain.cours.Cours;
import com.pfaraco.quiz.server.domain.sequence.Sequence;
import com.pfaraco.quiz.server.enums.SessionStatus;
@Entity
@NamedQueries({
	@NamedQuery(name = "findAllSessions", query="select o from SessionQuiz o"),
	@NamedQuery(name = "findSessionsByProfessor", query="select o from SessionQuiz o  WHERE o.cours.user.id = :userid "),
	@NamedQuery(name = "findSessionsByCours", query="select o from SessionQuiz o WHERE o.cours.id = :coursid "),
	@NamedQuery(name = "findSessionsByAuditor", query="select o from SessionQuiz o  WHERE  :user in o.cours.subscribers "),
	@NamedQuery(name = "findSessionsByAuditorAndCours", query="select o from SessionQuiz o  WHERE  :user in o.cours.subscribers "
			+ "and o.cours.id = :coursid"),
})


@Table(name = "session_quiz")
public class SessionQuiz extends DomainObject  implements  Serializable {
	@Id
	@GeneratedValue
	@Column(name="id")
	long id;
	@Column(name="status")
	SessionStatus status;
	@Column(name="start_date")
	Date startDate;
	@Column(name="end_date")
	Date endDate;
	@ManyToOne(fetch =FetchType.LAZY)
	@JoinColumn(name ="cours_id", nullable = false)
	Cours cours;
	@ManyToOne(fetch =FetchType.LAZY)
	@JoinColumn(name ="sequence_id", nullable = false)
	Sequence sequence;
		
	public SessionQuiz(long id, SessionStatus status, Date startDate,
			Date endDate, Cours cours,Sequence sequence) {
		super();
		this.id = id;
		this.status = status;
		this.startDate = startDate;
		this.endDate = endDate;
		this.cours = cours;
		this.sequence = sequence;
	}

	public SessionQuiz(SessionStatus status, Date startDate, Date endDate,
			Cours cours,Sequence sequence) {
		super();
		this.status = status;
		this.startDate = startDate;
		this.endDate = endDate;
		this.cours = cours;
		this.sequence = sequence;
	}

	public SessionQuiz() {
		super();
	}

	public SessionStatus getStatus() {
		return status;
	}

	public void setStatus(SessionStatus status) {
		this.status = status;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Cours getCours() {
		return cours;
	}

	public void setCours(Cours cours) {
		this.cours = cours;
	}

	public Sequence getSequence() {
		return sequence;
	}

	public void setSequence(Sequence sequence) {
		this.sequence = sequence;
	}

	@Override
	public long getId() {
		// TODO Auto-generated method stub
		return id;
	}

	@Override
	public void setId(long id) {
		this.id=id;// TODO Auto-generated method stub
		
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cours == null) ? 0 : cours.hashCode());
		result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result
				+ ((sequence == null) ? 0 : sequence.hashCode());
		result = prime * result
				+ ((startDate == null) ? 0 : startDate.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
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
		SessionQuiz other = (SessionQuiz) obj;
		if (cours == null) {
			if (other.cours != null)
				return false;
		} else if (!cours.equals(other.cours))
			return false;
		if (endDate == null) {
			if (other.endDate != null)
				return false;
		} else if (!endDate.equals(other.endDate))
			return false;
		if (id != other.id)
			return false;
		if (sequence == null) {
			if (other.sequence != null)
				return false;
		} else if (!sequence.equals(other.sequence))
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		if (status != other.status)
			return false;
		return true;
	}


}
