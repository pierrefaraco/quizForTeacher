package com.pfaraco.quiz.server.service.topic;

import java.util.List;
import com.pfaraco.quiz.server.domain.topic.Topic;

public interface TopicService {
	
	   	Topic findById(long id);
	     
	    Topic findByName(String name);
	     
	    void saveTopic(Topic topic);
	     
	    void updateTopic(Topic topic);
	     
	    void deleteTopicById(long id);
	 
	    List<Topic> findAllTopics(); 
	     
	    void deleteAllTopics();
	     
	    public boolean isTopicExist(Topic topic);
	    
}
