package com.pfaraco.quiz.server.service.topic;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pfaraco.quiz.server.domain.topic.Topic;
import com.pfaraco.quiz.server.domain.topic.TopicDao;
import com.pfaraco.quiz.server.domain.topic.TopicDaoImpl;
import com.pfaraco.quiz.server.domain.user.User;

@Service("topicService")
@Transactional
public class TopicServiceImpl implements  TopicService{
	 private static List<Topic> topics;
	 private static final AtomicLong counter = new AtomicLong();  
	 
	 @Autowired
	 TopicDao topicDao;
	 
	 static{
	        topics= populateDummyTopics();
	    }
	 
	@Override
	public Topic findById(long id) {
		  
	        return  topicDao.find(id);
	}

	@Override
	public Topic findByName(String name) {
		   for(Topic topic : topics){
	            if(topic.getName().equalsIgnoreCase(name)){
	                return topic;
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
	public void updateTopic(Topic topic) {
		   int index = topics.indexOf(topic);
	       topics.set(index, topic);
		
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
	public java.util.List<Topic> findAllTopics() {
		return topicDao.findAll();
		//return topicDao.findByUser(user);
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
