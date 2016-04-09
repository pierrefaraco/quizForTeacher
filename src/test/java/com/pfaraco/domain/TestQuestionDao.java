package com.pfaraco.domain;

import static org.junit.Assert.*;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;





import org.springframework.test.context.transaction.TransactionConfiguration;

import com.pfaraco.quiz.server.config.PersistenceJPAConfig;
import com.pfaraco.quiz.server.domain.questions.Question;
import com.pfaraco.quiz.server.domain.questions.QuestionDao;
import com.pfaraco.quiz.server.domain.questions.QuestionDaoImpl;
import com.pfaraco.quiz.server.domain.topic.Topic;
import com.pfaraco.quiz.server.domain.topic.TopicDao;
import com.pfaraco.quiz.server.domain.topic.TopicDaoImpl;
import com.pfaraco.quiz.server.domain.user.User;
import com.pfaraco.quiz.server.domain.user.UserDao;
import com.pfaraco.quiz.server.domain.user.UserDaoImpl;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { PersistenceJPAConfig.class,
		UserDaoImpl.class,TopicDaoImpl.class ,QuestionDaoImpl.class })

@Transactional
@Rollback(false)
public class TestQuestionDao {
	
	
	@Autowired
	TopicDao topicDao;
	@Autowired
	UserDao userDao;
	@Autowired
	QuestionDao questionDao;
	
	@Test
	public void testAutoId() {	
		User user = EntitiesCreator.createRandomUser();
		userDao.save(user);	
		Topic topic = EntitiesCreator.createRandomTopic(user);	
		Topic topic2 = EntitiesCreator.createRandomTopic(user);	
		topicDao.save(topic);
		topicDao.save(topic2);
		Question question1  = EntitiesCreator.createRandomQuestion(topic);
		Question question2  = EntitiesCreator.createRandomQuestion(topic2);

		questionDao.save(question1);
		questionDao.save(question2);
	
		assertNotNull(question1 .getId());
		assertNotNull(question2 .getId());
		assertTrue(question1 .getId()!= question2 .getId());
						
	}
	@Test
	public void testSaveQuestion() {	
		User user = EntitiesCreator.createRandomUser();
		userDao.save(user);	
		Topic topic = EntitiesCreator.createRandomTopic(user);	
		topicDao.save(topic);
		int questionCount = questionDao.findAll().size();		
		int t = 5000;
		for (int i = 0;i<t;i++)
			questionDao.save(EntitiesCreator.createRandomQuestion(topic));		
		assertEquals( questionCount + t ,questionDao.findAll().size());
		
		List <Question> questions=  questionDao.findAll();
		for (Question q:questions)
			{
			System.out.println("###################################################################################################");
			System.out.println("id : " + q.getId());
			System.out.println("point : " + q.getPoints());
			System.out.println("pos: " + q.getPosition());
			System.out.println("question : " + q.getQuestion());
			List <String>props = q.getPropositions();
			List <String>answers = q.getAnswers();
			for (String p:props)
				System.out.println("	*"+p);
			for (String a:answers)
				System.out.println("	*"+a);
			
			if(topic!=null){
				System.out.println("topic: " + q.getTopic().getName());
				System.out.println("topic: " + q.getTopic().getDescription());
				}
			else{
				System.out.println("lazy mode");	
				}
			}
		
	}

	
}
