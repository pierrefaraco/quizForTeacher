package com.pfaraco.quiz.server.domain;

/**
 * Every domain object should extend this abstract class.
 */
public abstract class DomainObject {

	public abstract long getId();

	public abstract void setId(long id);

	public String getCname() {
		return this.getClass().getName();
	}
}
