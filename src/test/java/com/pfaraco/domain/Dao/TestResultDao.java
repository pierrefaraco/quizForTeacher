package com.pfaraco.domain.Dao;

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
import com.pfaraco.quiz.server.domain.result.Result;
import com.pfaraco.quiz.server.domain.result.ResultDao;
import com.pfaraco.quiz.server.domain.result.ResultDaoImpl;
import com.pfaraco.quiz.server.domain.sequence.Sequence;
import com.pfaraco.quiz.server.domain.sequence.SequenceDao;
import com.pfaraco.quiz.server.domain.sequence.SequenceDaoImpl;
import com.pfaraco.quiz.server.domain.sessionquiz.SessionQuiz;
import com.pfaraco.quiz.server.domain.sessionquiz.SessionQuizDao;
import com.pfaraco.quiz.server.domain.sessionquiz.SessionQuizDaoImpl;
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
		TopicDaoImpl.class, QuestionDaoImpl.class, SequenceDaoImpl.class ,ResultDaoImpl.class })
@Transactional(rollbackOn = Exception.class)
@Rollback(false)
public class TestResultDao extends TestCase {
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
	@Autowired
	ResultDao resultDao;
	
	@Test
	public void testAutoId() {
		User user = EntitiesCreator.createRandomUser(AccountType.PROFESSOR);
		userDao.save(user);
		Cours cours = EntitiesCreator.createRandomCours(true, user, null);
		coursDao.save(cours);
		Sequence sequence = EntitiesCreator.createRandomSequence(user, null);
		sequenceDao.save(sequence);
		SessionQuiz sessionQuiz = EntitiesCreator.createRandomSessionQuiz(
				SessionStatus.RUNNING, cours, sequence);	
		sessionQuizDao.save(sessionQuiz);
		User auditor = EntitiesCreator.createRandomUser(AccountType.PROFESSOR);
		userDao.save(auditor);
		Result result1  =  EntitiesCreator.createRandomResult(auditor, sessionQuiz);
		resultDao.save(result1);
		Result result2  =  EntitiesCreator.createRandomResult(auditor, sessionQuiz);
		resultDao.save(result2);
		
		assertNotNull(result1 .getId());
		assertNotNull(result2 .getId());
		assertTrue(result1 .getId()!= result2 .getId());

	}
		
}
