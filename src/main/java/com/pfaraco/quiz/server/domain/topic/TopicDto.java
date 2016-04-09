package com.pfaraco.quiz.server.domain.topic;

public class TopicDto {
	private long id;
	private String name;
	private String description;
	
	
	public TopicDto(long id, String name, String description) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
	}
	
	public TopicDto(Topic topic) {
		super();
		this.id = topic.getId();
		this.name = topic.getName();
		this.description = topic.getDescription();
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
	
	
}
