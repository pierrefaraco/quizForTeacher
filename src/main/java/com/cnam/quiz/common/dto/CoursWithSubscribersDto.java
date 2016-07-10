package com.cnam.quiz.common.dto;

import java.util.HashMap;
import java.util.Map;

import com.cnam.quiz.common.enums.SubscriberStatus;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(using = CoursWithSubscribersDtoSerializer.class)
@JsonDeserialize(using = CoursWithSubscribersDtoDeserializer.class)
public class CoursWithSubscribersDto {
	private long id;
	private String name;
	private String description;
	boolean active;
	private long userId;
	private HashMap <UserDto,SubscriberStatus> subscribers;
	
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
	public Map <UserDto, SubscriberStatus> getSubscribers() {
		return subscribers;
	}
	public void setSubscribers(HashMap<UserDto, SubscriberStatus> subscribers) {
		this.subscribers = subscribers;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (active ? 1231 : 1237);
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((subscribers == null) ? 0 : subscribers.hashCode());
		result = prime * result + (int) (userId ^ (userId >>> 32));
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
		CoursWithSubscribersDto other = (CoursWithSubscribersDto) obj;
		if (active != other.active)
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (subscribers == null) {
			if (other.subscribers != null)
				return false;
		} else if (!subscribers.equals(other.subscribers))
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}



}
