package com.cnam.quiz.common.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.cnam.quiz.common.enums.SessionStatus;
import com.cnam.quiz.server.domain.cours.Cours;
import com.cnam.quiz.server.domain.sequence.Sequence;

public class SessionQuizDto implements Serializable {
	long id;
	SessionStatus status;
	Date startDate;
	Date endDate;
	long coursId;
	long sequenceId;
	
	public SessionQuizDto() {

	}

	public SessionQuizDto(long id, SessionStatus status, Date startDate,
			Date endDate, long coursId, long sequenceId) {
		super();
		this.id = id;
		this.status = status;
		this.startDate = startDate;
		this.endDate = endDate;
		this.coursId = coursId;
		this.sequenceId = sequenceId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public long getCoursId() {
		return coursId;
	}

	public void setCoursId(long coursId) {
		this.coursId = coursId;
	}

	public long getSequenceId() {
		return sequenceId;
	}

	public void setSequenceId(long sequenceId) {
		this.sequenceId= sequenceId;
	}
	
	
	
	
}
