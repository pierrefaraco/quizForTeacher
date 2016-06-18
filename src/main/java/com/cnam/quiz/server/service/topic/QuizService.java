package com.cnam.quiz.server.service.topic;

import java.util.List;

import com.cnam.quiz.common.dto.TopicDto;
import com.cnam.quiz.server.domain.topic.Topic;

public interface QuizService {
	
	   	TopicDto findById(long id);
	     
	    TopicDto findByName(String name);
	     
	    void saveTopic(Topic topic);
	     
	    void updateTopic(TopicDto topic);
	     
	    void deleteTopicById(long id);
	 
	    List<TopicDto> findAllTopics(); 
	     
	    void deleteAllTopics();
	     
	    public boolean isTopicExist(Topic topic);
	    
}
