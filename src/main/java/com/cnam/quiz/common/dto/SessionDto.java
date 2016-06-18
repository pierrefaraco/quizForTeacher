package com.cnam.quiz.common.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.cnam.quiz.common.enums.SessionStatus;
import com.cnam.quiz.server.domain.cours.Cours;
import com.cnam.quiz.server.domain.sequence.Sequence;

public class SessionDto {
	long id;
	SessionStatus status;
	Date startDate;
	Date endDate;
	Cours cours;
	Sequence sequence;
	
	public SessionDto() {

	}

	public SessionDto(long id, SessionStatus status, Date startDate,
			Date endDate, Cours cours, Sequence sequence) {
		super();
		this.id = id;
		this.status = status;
		this.startDate = startDate;
		this.endDate = endDate;
		this.cours = cours;
		this.sequence = sequence;
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
	
	
	
	
}
