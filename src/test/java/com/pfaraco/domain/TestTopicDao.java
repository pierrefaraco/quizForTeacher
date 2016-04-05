package com.pfaraco.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import junit.framework.Assert;

import org.easymock.EasyMock;
import org.hibernate.Session;
import org.hibernate.annotations.common.AssertionFailure;
import org.junit.Before;
import org.junit.Test;
import org.junit.internal.runners.statements.Fail;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.web.bind.annotation.RestController;

import com.pfaraco.App;
import com.pfaraco.quiz.server.config.MVCConfig;
import com.pfaraco.quiz.server.config.PersistenceJPAConfig;
import com.pfaraco.quiz.server.domain.topic.Topic;
import com.pfaraco.quiz.server.domain.topic.TopicDao;
import com.pfaraco.quiz.server.domain.topic.TopicDaoImpl;
import com.pfaraco.quiz.server.domain.user.User;
import com.pfaraco.quiz.server.domain.user.UserDao;
import com.pfaraco.quiz.server.domain.user.userDaoImpl;
import com.pfaraco.quiz.server.service.topic.TopicService;
import com.pfaraco.quiz.server.util.persistence.AbstractDataAccessObject;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { PersistenceJPAConfig.class,
		TopicDaoImpl.class,userDaoImpl.class })
@Component
@Transactional
public class TestTopicDao {
	@Resource
	private PersistenceJPAConfig m_oApplicationContext;

	@Autowired
	TopicDao topicDao;
	@Autowired
	UserDao userDao;

	@Test
	public void testCreate() {
		try {
			User user = new User(1, "pierre", "faraco", new SimpleDateFormat(
					"dd-MM-yyyy").parse("04-02-1981"),
					"pierre.faraco@gmail.com", "salut , je me presente",
					"admin", "admin", 2);

			Topic topic1 = new Topic(1, "Les rois de frances",
					"Ils étaient puissants", user);
			
			Topic topic2 = new Topic(2, "Les clochard",
					"Qu'il est difficile de vire dans la rue", user);
			
			Topic topic3 = new Topic(3, "Les africains",
					"La culture Africaine ! Quel pation !  ", user);

			userDao.save(user);
			topicDao.save(topic1);
			topicDao.save(topic2);
			topicDao.save(topic3);
			
			
			List <Topic> topics =  topicDao.findAll();
			
			System.out.println(topics.size());
			
			
		
		} catch (Exception e) {
			e.printStackTrace();

		}

	}
}
