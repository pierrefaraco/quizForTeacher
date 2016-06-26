package com.cnam.quiz.common.dto;

import java.util.Map;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyJoinColumn;

import com.cnam.quiz.common.enums.SubscriberStatus;
import com.cnam.quiz.server.domain.user.User;

public class CoursDto {
	private long id;
	private String name;
	private String description;
	boolean active;
	private long userId;
	private Map <User,SubscriberStatus> subscribers;
	
	public CoursDto() {
	}
	
	public CoursDto(long id, String name, String description, boolean active,
			long userId, Map<User, SubscriberStatus> subscribers) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.active = active;
		this.userId = userId;
		this.subscribers = subscribers;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public Map<User, SubscriberStatus> getSubscribers() {
		return subscribers;
	}
	public void setSubscribers(Map<User, SubscriberStatus> subscribers) {
		this.subscribers = subscribers;
	}
		
}
