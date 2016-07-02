package cnam.glg204.domain.Dao;


import java.util.List;

import javax.transaction.Transactional;

import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cnam.quiz.common.config.PersistenceJPAConfig;
import com.cnam.quiz.common.enums.AccountType;
import com.cnam.quiz.common.enums.QuestionType;
import com.cnam.quiz.server.domain.questions.Question;
import com.cnam.quiz.server.domain.questions.QuestionDao;
import com.cnam.quiz.server.domain.questions.QuestionDaoImpl;
import com.cnam.quiz.server.domain.topic.Topic;
import com.cnam.quiz.server.domain.topic.TopicDao;
import com.cnam.quiz.server.domain.topic.TopicDaoImpl;
import com.cnam.quiz.server.domain.user.User;
import com.cnam.quiz.server.domain.user.UserDao;
import com.cnam.quiz.server.domain.user.UserDaoImpl;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { PersistenceJPAConfig.class,
		UserDaoImpl.class,TopicDaoImpl.class ,QuestionDaoImpl.class })

@Transactional(rollbackOn = Exception.class)
@Rollback(false)
public class TestQuestionDao  extends TestCase {
	
	
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
		Question question1  = EntitiesCreator.createRandomQuestion(topic,QuestionType.QUIZ);
		Question question2  = EntitiesCreator.createRandomQuestion(topic2,QuestionType.QUIZ);

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
		int t = 50;
		for (int i = 0;i<t;i++)
			questionDao.save(EntitiesCreator.createRandomQuestion(topic,QuestionType.QUIZ));		
		assertEquals( questionCount + t ,questionDao.findAll().size());			
		List<Question> g = questionDao.findQuestionsByTopic(topic);
		for (Question question : g )
			assertEquals(topic.getId() , question.getTopic().getId());				
	}
	
	@Test
	public void findQuestionsByTopic() {	
		User user = EntitiesCreator.createRandomUser();
		user.setAccountType(AccountType.PROFESSOR);
		userDao.save(user);	
		Topic topic = EntitiesCreator.createRandomTopic(user);	
		topicDao.save(topic);
		for (int i = 0;i <10 ;i++)
			questionDao.save(EntitiesCreator.createRandomQuestion(topic,QuestionType.QUIZ));	
				
		int questionCount = questionDao.findQuestionsByTopic(topic).size();				
		assertEquals( questionCount , 10);				
	}


	
}
