package com.pfaraco.domain.Dao;

import static org.junit.Assert.*;

import javax.transaction.Transactional;

import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.pfaraco.quiz.server.config.PersistenceJPAConfig;
import com.pfaraco.quiz.server.domain.cours.Cours;
import com.pfaraco.quiz.server.domain.cours.CoursDao;
import com.pfaraco.quiz.server.domain.cours.CoursDaoImpl;
import com.pfaraco.quiz.server.domain.questions.QuestionDao;
import com.pfaraco.quiz.server.domain.questions.QuestionDaoImpl;
import com.pfaraco.quiz.server.domain.sequence.Sequence;
import com.pfaraco.quiz.server.domain.sequence.SequenceDao;
import com.pfaraco.quiz.server.domain.sequence.SequenceDaoImpl;
import com.pfaraco.quiz.server.domain.sessionquiz.SessionQuiz;
import com.pfaraco.quiz.server.domain.sessionquiz.SessionQuizDao;
import com.pfaraco.quiz.server.domain.sessionquiz.SessionQuizDaoImpl;
import com.pfaraco.quiz.server.domain.topic.Topic;
import com.pfaraco.quiz.server.domain.topic.TopicDao;
import com.pfaraco.quiz.server.domain.topic.TopicDaoImpl;
import com.pfaraco.quiz.server.domain.user.User;
import com.pfaraco.quiz.server.domain.user.UserDao;
import com.pfaraco.quiz.server.domain.user.UserDaoImpl;
import com.pfaraco.quiz.server.enums.AccountType;
import com.pfaraco.quiz.server.enums.SessionStatus;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { PersistenceJPAConfig.class,
		CoursDaoImpl.class, SessionQuizDaoImpl.class, UserDaoImpl.class,
		TopicDaoImpl.class, QuestionDaoImpl.class, SequenceDaoImpl.class })
@Transactional(rollbackOn = Exception.class)
@Rollback(false)
public class TestSessionQuizDao extends TestCase {
	@Autowired
	CoursDao coursDao;
	@Autowired
	SessionQuizDao sessionQuizDao;
	@Autowired
	TopicDao topicDao;
	@Autowired
	UserDao userDao;
	@Autowired
	QuestionDao questionDao;
	@Autowired
	SequenceDao sequenceDao;

	@Test
	public void testAutoId() {
		User user = EntitiesCreator.createRandomUser(AccountType.PROFESSOR);
		userDao.save(user);
		Cours cours = EntitiesCreator.createRandomCours(true, user, null);
		coursDao.save(cours);
		Sequence sequence = EntitiesCreator.createRandomSequence(user, null);
		sequenceDao.save(sequence);
		SessionQuiz sessionQuiz1 = EntitiesCreator.createRandomSessionQuiz(
				SessionStatus.RUNNING, cours, sequence);
		SessionQuiz sessionQuiz2 = EntitiesCreator.createRandomSessionQuiz(
				SessionStatus.RUNNING, cours, sequence);
		sessionQuizDao.save(sessionQuiz1);
		sessionQuizDao.save(sessionQuiz2);

		assertNotNull(sessionQuiz1.getId());
		assertNotNull(sessionQuiz2.getId());
		assertTrue(sessionQuiz1.getId() != sessionQuiz2.getId());
	}

	@Test
	public void testChangeState() {
		int countSession = sessionQuizDao.findAll().size();

		User user = EntitiesCreator.createRandomUser(AccountType.PROFESSOR);
		;
		userDao.save(user);
		Cours cours = EntitiesCreator.createRandomCours(true, user, null);
		coursDao.save(cours);
		Topic topic = EntitiesCreator.createRandomTopic(user);
		topicDao.save(topic);
		Sequence sequence = EntitiesCreator.createRandomSequence(user,
				EntitiesCreator.createListOfQuestions(questionDao, 10, topic));
		sequenceDao.save(sequence);
		SessionQuiz sessionQuiz1 = EntitiesCreator.createRandomSessionQuiz(
				SessionStatus.RUNNING, cours, sequence);
		sessionQuizDao.save(sessionQuiz1);

		assertEquals(sessionQuizDao.find(sessionQuiz1.getId()).getStatus(),
				SessionStatus.RUNNING);

		SessionQuiz sessionQuiz2 = sessionQuizDao.find(sessionQuiz1.getId());
		sessionQuiz2.setStatus(SessionStatus.NOT_RUNNING);

		assertEquals(countSession + 1, sessionQuizDao.findAll().size());
		assertEquals(sessionQuizDao.find(sessionQuiz1.getId()).getStatus(),
				SessionStatus.NOT_RUNNING);

	}

	@Test
	public void testSequenceContain() {
		int countSession = sessionQuizDao.findAll().size();
		int suscriberCount = 25;
		int questionCount = 35;
		User user = EntitiesCreator.createRandomUser(AccountType.PROFESSOR);
		userDao.save(user);
		Cours cours = EntitiesCreator
				.createRandomCours(true, user, EntitiesCreator
						.createMapOfSubscribers(suscriberCount, userDao));
		coursDao.save(cours);
		Topic topic = EntitiesCreator.createRandomTopic(user);
		topicDao.save(topic);
		Sequence sequence = EntitiesCreator.createRandomSequence(user,
				EntitiesCreator.createListOfQuestions(questionDao,
						questionCount, topic));
		sequenceDao.save(sequence);
		SessionQuiz sessionQuiz1 = EntitiesCreator.createRandomSessionQuiz(
				SessionStatus.RUNNING, cours, sequence);
		sessionQuizDao.save(sessionQuiz1);

		assertEquals(suscriberCount, sessionQuizDao.find(sessionQuiz1.getId())
				.getCours().getSubscribers().size());
		assertEquals(questionCount, sessionQuizDao.find(sessionQuiz1.getId())
				.getSequence().getQuestions().size());
	}
}
