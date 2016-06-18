package com.cnam.quiz.server.service.topic;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cnam.quiz.common.dto.TopicDto;
import com.cnam.quiz.server.domain.topic.Topic;
import com.cnam.quiz.server.domain.topic.TopicDao;
import com.cnam.quiz.server.domain.topic.TopicDaoImpl;
import com.cnam.quiz.server.domain.user.User;

@Service("quizService")
@Transactional
public class QuizServiceImpl implements  QuizService{
	 private static List<Topic> topics;
	 private static final AtomicLong counter = new AtomicLong();  
	 
	 @Autowired
	 TopicDao topicDao;
	 
	 static{
	        topics= populateDummyTopics();
	    }
	 
	@Override
	public TopicDto findById(long id) {
			Topic topic = topicDao.find(id);
			if (topic!=null)
				return new TopicDto(topic);
			return null;
	}

	@Override
	public TopicDto findByName(String name) {
		   for(Topic topic : topics){
	            if(topic.getName().equalsIgnoreCase(name)){
	                //return topic;
	            }
	        }
	        return null;
	}

	@Override
	public void saveTopic(Topic topic) {
	      topic.setId(counter.incrementAndGet());
	      topics.add(topic);	
	}

	@Override
	public void updateTopic(TopicDto topic) {
		   //int index = topics.indexOf(topic);
	      // topics.set(index, topic);
		
	}

	@Override
	public void deleteTopicById(long id) {
		  for (Iterator<Topic> iterator = topics.iterator(); iterator.hasNext(); ) {
	            Topic topic = iterator.next();
	            if (topic.getId() == id) {
	                iterator.remove();
	            }
	        }	
	}

	@Override
	public java.util.List<TopicDto> findAllTopics() {
		List<Topic> topics = topicDao.findAll();
		List<TopicDto> topicsDto = new ArrayList<TopicDto>();
		for (Topic topic :topics){
			topicsDto.add(new TopicDto(topic));
		}
		return topicsDto ;
	}

	@Override
	public void deleteAllTopics() {
		 topics.clear();		
	}

	@Override
	public boolean isTopicExist(Topic topic) {
		 return findByName(topic.getName())!=null;
	}

    private static List<Topic> populateDummyTopics(){
        List<Topic> topics = new ArrayList<Topic>();
        topics .add(new Topic(counter.incrementAndGet(),"Java", "NY",null));
        topics .add(new Topic(counter.incrementAndGet(),"C++", "ALBAMA",null));
        topics .add(new Topic(counter.incrementAndGet(),"Angular_js", "NEBRASKA",null));
        topics .add(new Topic(counter.incrementAndGet(),"Java_script", "Le Java_script c'est dure",null));
        topics .add(new Topic(counter.incrementAndGet(),"Java_script2", "Le Java_script 1",null));
        topics .add(new Topic(counter.incrementAndGet(),"Java_script3", "Le Java_script 3",null));
        return topics;
    }
}
