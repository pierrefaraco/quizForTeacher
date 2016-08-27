package cnam.glg204.domain.Dao;

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

import junit.framework.TestCase;

import org.hibernate.annotations.common.AssertionFailure;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.bind.annotation.RestController;

import cnam.glg204.quiz.common.config.App;
import cnam.glg204.quiz.common.config.MVCConfig;
import cnam.glg204.quiz.common.config.PersistenceJPAConfig;
import cnam.glg204.quiz.server.domain.questions.QuestionDaoImpl;
import cnam.glg204.quiz.server.domain.topic.Topic;
import cnam.glg204.quiz.server.domain.topic.TopicDao;
import cnam.glg204.quiz.server.domain.topic.TopicDaoImpl;
import cnam.glg204.quiz.server.domain.user.User;
import cnam.glg204.quiz.server.domain.user.UserDao;
import cnam.glg204.quiz.server.domain.user.UserDaoImpl;
import cnam.glg204.quiz.server.service.quiz.QuizService;
import cnam.glg204.quiz.server.util.persistence.AbstractDataAccessObject;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { PersistenceJPAConfig.class,
		UserDaoImpl.class, TopicDaoImpl.class, QuestionDaoImpl.class })
@Transactional(rollbackOn = Exception.class)
@Rollback(true)
public class TestTopicDao extends TestCase {

	@Autowired
	TopicDao topicDao;
	@Autowired
	UserDao userDao;

	@Test
	public void testAutoId() {
		User user = EntitiesCreator.createRandomUser();
		userDao.save(user);
		Topic topic = EntitiesCreator.createRandomTopic(user);
		Topic topic2 = EntitiesCreator.createRandomTopic(user);
		topicDao.save(topic);
		topicDao.save(topic2);
		assertNotNull(topic.getId());
		assertNotNull(topic2.getId());
		assertTrue(topic.getId() != topic2.getId());
	}

	@Test
	public void testSaveTopic() {
		User user = EntitiesCreator.createRandomUser();
		userDao.save(user);
		int topicsCount = topicDao.findAll().size();
		int t = 5;
		for (int i = 0; i < t; i++)
			topicDao.save(EntitiesCreator.createRandomTopic(user));
		assertEquals(topicsCount + t, topicDao.findAll().size());
	}

	@Test
	public void testDeleteTopic() {
		User user = EntitiesCreator.createRandomUser();
		userDao.save(user);
		int topicsCount = topicDao.findAll().size();
		Topic topic = EntitiesCreator.createRandomTopic(user);
		topicDao.delete(topic);
		assertEquals(" no topic should be registered  ", topicsCount, topicDao
				.findAll().size());
		topicDao.save(topic);
		assertEquals(" the topic hasn't been registered ", topicsCount + 1,
				topicDao.findAll().size());
		topicDao.delete(topic);
		assertEquals(" the topic hasn't been registered ", topicsCount,
				topicDao.findAll().size());
	}

	@Test
	public void testUpdateTopic() {
		User user = EntitiesCreator.createRandomUser();
		userDao.save(user);
		int topicsCount = topicDao.findAll().size();
		Topic topic = EntitiesCreator.createRandomTopic(user);
		topicDao.save(topic);
		long id = topic.getId();
		assertEquals(" the topic hasn't been registered ", topicsCount + 1,
				topicDao.findAll().size());
		Topic topic2 = EntitiesCreator.createRandomTopic(user);
		topic2.setId(id);
		topicDao.update(topic2);
		assertEquals(" the topic hasn't been merged ", topicsCount + 1,
				topicDao.findAll().size());
		Topic topic3 = EntitiesCreator.createRandomTopic(user);
		topicDao.update(topic3);
		assertEquals(" the topic has been merged ", topicsCount + 2, topicDao
				.findAll().size());

	}

	@Test
	public void testFindTopicById() {
		User user = EntitiesCreator.createRandomUser();
		userDao.save(user);
		int topicsCount = topicDao.findAll().size();
		Topic topic1 = EntitiesCreator.createRandomTopic(user);
		topicDao.save(topic1);
		long id = topic1.getId();
		assertEquals(" the topic hasn't been registered ", topicsCount + 1,
				topicDao.findAll().size());
		Topic topic2 = topicDao.find(id);
		assertTrue(" topic1 did'nt find in database ", topic2.equals(topic1));
	}

	@Test
	public void testFindTopicByUser() {

		User user1 = EntitiesCreator.createRandomUser();
		User user2 = EntitiesCreator.createRandomUser();
		userDao.save(user1);
		userDao.save(user2);
		int topicCount1 = topicDao.findByUser(user1).size();
		int topicCount2 = topicDao.findByUser(user2).size();
		for (int i = 0; i < 5; i++) {
			Topic topic = EntitiesCreator.createRandomTopic(user1);
			topicDao.save(topic);
		}
		for (int i = 0; i < 7; i++) {
			Topic topic = EntitiesCreator.createRandomTopic(user2);
			topicDao.save(topic);
		}

		
		assertEquals(topicCount1 + 5, topicDao.findByUser(user1).size());
		assertEquals(topicCount2 + 7, topicDao.findByUser(user2).size());
	}

	@Test
	public void testFindAll() {
		User user = EntitiesCreator.createRandomUser();
		userDao.save(user);
		int t = 1000;
		List<Topic> topics = new ArrayList();
		for (int i = 0; i < t; i++) {
			Topic topic = EntitiesCreator.createRandomTopic(user);
			topics.add(topic);
			topicDao.save(topic);
		}
		List<Topic> topics2 = topicDao.findAll();

		for (int i = 0; i < t; i++)
			assertTrue(" topic " + i + " did'nt find in database ",
					topics2.contains(topics.get(i)));

	}

}
