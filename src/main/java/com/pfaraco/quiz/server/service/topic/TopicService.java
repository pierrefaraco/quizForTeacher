package com.pfaraco.quiz.server.service.topic;

import java.util.List;

import com.pfaraco.quiz.server.domain.topic.Topic;
import com.pfaraco.quiz.server.domain.topic.TopicDto;

public interface TopicService {
	
	   	TopicDto findById(long id);
	     
	    TopicDto findByName(String name);
	     
	    void saveTopic(Topic topic);
	     
	    void updateTopic(TopicDto topic);
	     
	    void deleteTopicById(long id);
	 
	    List<TopicDto> findAllTopics(); 
	     
	    void deleteAllTopics();
	     
	    public boolean isTopicExist(Topic topic);
	    
}
