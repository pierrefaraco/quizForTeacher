package com.cnam.quiz.server.domain;

import com.cnam.quiz.common.exceptions.CheckException;

/**
 * Every domain object should extend this abstract class.
 */
public abstract class DomainObject {

	public abstract long getId();

	public abstract void setId(long id);
	
	public abstract void checkData()throws CheckException;

	public String getCname() {
		return this.getClass().getName();
	}
}
