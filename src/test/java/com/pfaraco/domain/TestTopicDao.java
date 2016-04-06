package com.pfaraco.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.hibernate.annotations.common.AssertionFailure;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.bind.annotation.RestController;
import com.pfaraco.App;
import com.pfaraco.quiz.server.config.MVCConfig;
import com.pfaraco.quiz.server.config.PersistenceJPAConfig;
import com.pfaraco.quiz.server.domain.topic.Topic;
import com.pfaraco.quiz.server.domain.topic.TopicDao;
import com.pfaraco.quiz.server.domain.topic.TopicDaoImpl;
import com.pfaraco.quiz.server.domain.user.User;
import com.pfaraco.quiz.server.domain.user.UserDao;
import com.pfaraco.quiz.server.domain.user.UserDaoImpl;
import com.pfaraco.quiz.server.service.topic.TopicService;
import com.pfaraco.quiz.server.util.persistence.AbstractDataAccessObject;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { PersistenceJPAConfig.class,
		UserDaoImpl.class,TopicDaoImpl.class })
@Component
@Transactional
public class TestTopicDao {

	@Autowired
	TopicDao topicDao;
	@Autowired
	UserDao userDao;



	
	@Test
	public void testAutoId() {	
		User user = TestUserDao.createRandomUser();
		userDao.save(user);	
		Topic topic = createRandomTopic(user);	
		Topic topic2 = createRandomTopic(user);	
		topicDao.save(topic);
		topicDao.save(topic2);
		assertNotNull(topic .getId());
		assertNotNull(topic2 .getId());
		assertTrue(topic .getId()!= topic2 .getId());
	}
		
	@Test
	public void testCreateTopic() {	
		User user = TestUserDao.createRandomUser();
		userDao.save(user);	
		int topicsCount = topicDao.findAll().size();		
		int t = 5;
		for (int i = 0;i<t;i++)
			topicDao.save(createRandomTopic(user));		
		assertEquals( topicsCount + t ,topicDao.findAll().size());					
	}
	
	@Test
	public void testDeleteTopic() {	
		User user = TestUserDao.createRandomUser();
		userDao.save(user);	
		int topicsCount = topicDao.findAll().size();			
		Topic topic = createRandomTopic(user);
		topicDao.delete(topic);
		assertEquals(" no topic should be registered  ", topicsCount   ,topicDao.findAll().size());
		topicDao.save(topic);	
		assertEquals(" the topic hasn't been registered ", topicsCount + 1 ,topicDao.findAll().size());
		topicDao.delete(topic);
		assertEquals(" the topic hasn't been registered ", topicsCount  ,topicDao.findAll().size());		
	}
	
	@Test
	public void testUpdateTopic() {	
		User user = TestUserDao.createRandomUser();
		userDao.save(user);	
		int topicsCount = topicDao.findAll().size();		
		Topic topic = createRandomTopic(user);		
		topicDao.save(topic);
		long id= topic.getId();
		assertEquals(" the topic hasn't been registered ", topicsCount + 1 ,topicDao.findAll().size());
		Topic topic2 = createRandomTopic(user);
		topic2.setId(id);
		topicDao.update(topic2);
		assertEquals(" the topic hasn't been merged ", topicsCount + 1 ,topicDao.findAll().size());
		Topic topic3 = createRandomTopic(user);
		topicDao.update(topic3);
		assertEquals(" the topic has been merged ", topicsCount + 2 ,topicDao.findAll().size());
		
	}
	
	
	@Test
	public void testFindTopicById() {
		User user = TestUserDao.createRandomUser();
		userDao.save(user);	
		int topicsCount = topicDao.findAll().size();	
		Topic topic1 = createRandomTopic(user);	
		topicDao.save(topic1);
		long id= topic1.getId();
		assertEquals(" the topic hasn't been registered ", topicsCount + 1 ,topicDao.findAll().size());
		Topic topic2 = topicDao.find(id);
		assertTrue(" topic1 did'nt find in database ",topic2.equals(topic1));	
	}
		
	
	@Test
	public void testFindAll() {
		User user = TestUserDao.createRandomUser();
		userDao.save(user);	
		int t = 1000;
		List <Topic> topics  = new ArrayList();
		for (int i = 0;i<t;i++){
			Topic topic = createRandomTopic(user);
			topics.add(topic);
			topicDao.save(topic);
		}	
		List <Topic> topics2  = topicDao.findAll();
		
		for (int i = 0;i<t;i++)
			assertTrue(" topic "+ i +" did'nt find in database ",topics2.contains(topics.get(i)));	
												
	}
	
	public static Topic  createRandomTopic(User user){

		return createTopic(
				(int)(Math.random()* 10000) + "name",
				(int)(Math.random()* 10000) + "description",
				user
				);	
	}
	
	private static Topic createTopic(String topicName,String description,User user){
		return  new Topic(topicName, description, user);
	}
}
