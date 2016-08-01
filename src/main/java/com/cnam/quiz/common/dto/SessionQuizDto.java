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
	String startDate;
	String  endDate;
	long coursId;
	long sequenceId;
	
	public SessionQuizDto() {

	}

	public SessionQuizDto(long id, SessionStatus status, String  startDate,
			String endDate, long coursId, long sequenceId) {
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

	public String  getStartDate() {
		return startDate;
	}

	public void setStartDate(String  startDate) {
		this.startDate = startDate;
	}

	public String  getEndDate() {
		return endDate;
	}

	public void setEndDate(String  endDate) {
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



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (coursId ^ (coursId >>> 32));
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + (int) (sequenceId ^ (sequenceId >>> 32));
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
		SessionQuizDto other = (SessionQuizDto) obj;
		if (coursId != other.coursId)
			return false;
		if (id != other.id)
			return false;
		if (sequenceId != other.sequenceId)
			return false;
		if (status != other.status)
			return false;
		return true;
	}

	public void sysOutPrint (){
		
		System.out.println(id +" "+ coursId + " " + sequenceId+" " +startDate+ " "+endDate+" "+ status);
	}
	
	
	
}
