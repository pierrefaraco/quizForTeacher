package com.cnam.quiz.common.dto;

import java.io.Serializable;

import com.cnam.quiz.server.domain.topic.Topic;

public class TopicDto implements Serializable  {
	private long id;
	private String name;
	private String description;
	private long userId;
	public TopicDto(){}
	
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

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}
	

}
