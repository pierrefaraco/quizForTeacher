package cnam.glg204.domain.Dao;

import static org.junit.Assert.*;

import java.util.Map;

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
import com.cnam.quiz.common.enums.SubscriberStatus;
import com.cnam.quiz.server.domain.cours.Cours;
import com.cnam.quiz.server.domain.cours.CoursDao;
import com.cnam.quiz.server.domain.cours.CoursDaoImpl;
import com.cnam.quiz.server.domain.user.User;
import com.cnam.quiz.server.domain.user.UserDao;
import com.cnam.quiz.server.domain.user.UserDaoImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { PersistenceJPAConfig.class,
		UserDaoImpl.class, CoursDaoImpl.class })
@Transactional(rollbackOn = Exception.class)
@Rollback(true)
public class TestCoursDao extends TestCase  {
	@Autowired
	UserDao userDao;

	@Autowired
	CoursDao coursDao;

	@Test
	public void testAutoId() {
		User user = EntitiesCreator.createRandomUser();
		userDao.save(user);

		Cours cours1 = EntitiesCreator.createRandomCours(true, user, null);
		Cours cours2 = EntitiesCreator.createRandomCours(true, user, null);

		coursDao.save(cours1);
		coursDao.save(cours2);

		assertNotNull(coursDao.find(cours1.getId()).getId());
		assertNotNull(coursDao.find(cours2.getId()).getId());

		assertTrue(coursDao.find(cours1.getId()).getId() != coursDao.find(
				cours2.getId()).getId());
	}

	@Test
	public void testSuscribe() {
		User professor = EntitiesCreator
				.createRandomUser(AccountType.PROFESSOR);
		userDao.save(professor);
		Cours cours1 = EntitiesCreator.createRandomCours(true, professor, null);
		coursDao.save(cours1);

		Map<User, SubscriberStatus> subscribers = EntitiesCreator
				.createMapOfSubscribers(10, userDao);
		cours1.setSubscribers(EntitiesCreator.createMapOfSubscribers(10,
				userDao));
		assertEquals(10, coursDao.find(cours1.getId()).getSubscribers().size());

		User auditor = EntitiesCreator.createRandomUser(AccountType.AUDITOR);
		userDao.save(auditor);

		Cours cours2 = coursDao.find(cours1.getId());
		Map<User, SubscriberStatus> subscribers2 = cours2.getSubscribers();

		subscribers2.put(auditor, SubscriberStatus.WAITING_ANSWER);
		coursDao.save(cours2);

		assertEquals(11, coursDao.find(cours1.getId()).getSubscribers().size());
	}

	@Test
	public void testUnSuscribe() {
		User professor = EntitiesCreator
				.createRandomUser(AccountType.PROFESSOR);
		userDao.save(professor);
		Cours cours1 = EntitiesCreator.createRandomCours(true, professor, null);
		coursDao.save(cours1);

		Map<User, SubscriberStatus> subscribers = EntitiesCreator
				.createMapOfSubscribers(10, userDao);
		User auditor = EntitiesCreator.createRandomUser(AccountType.AUDITOR);
		userDao.save(auditor);
		subscribers.put(auditor, SubscriberStatus.WAITING_ANSWER);
		cours1.setSubscribers(subscribers);

		coursDao.save(cours1);
		assertEquals(11, coursDao.find(cours1.getId()).getSubscribers().size());

		Cours cours2 = coursDao.find(cours1.getId());
		subscribers = cours2.getSubscribers();

		subscribers.remove(auditor);
		assertEquals(10, coursDao.find(cours1.getId()).getSubscribers().size());
	}

	@Test
	public void testDeleteCours() {

		User professor = EntitiesCreator
				.createRandomUser(AccountType.PROFESSOR);
		;
		userDao.save(professor);
		Cours cours1 = EntitiesCreator.createRandomCours(true, professor, null);
		coursDao.save(cours1);
		Map<User, SubscriberStatus> subscribers = EntitiesCreator
				.createMapOfSubscribers(10, userDao);
		;
		cours1.setSubscribers(subscribers);
		assertEquals(10, coursDao.find(cours1.getId()).getSubscribers().size());
		int coursCount = coursDao.findAll().size();
		Cours cours2 = coursDao.find(cours1.getId());
		
		coursDao.delete(cours2);
		assertEquals(coursCount - 1, coursDao.findAll().size());
	}

	@Test
	public void testUserWhenIsSuscribedToACours() {
		/*
		int userCount = userDao.findAll().size();
		User professor = EntitiesCreator
				.createRandomUser(AccountType.PROFESSOR);
		userDao.save(professor);
		Cours cours1 = EntitiesCreator.createRandomCours(true, professor, null);
		coursDao.save(cours1);
		Map<User, SubscriberStatus> subscribers = EntitiesCreator
				.createMapOfSubscribers(1, userDao);
		User auditor = EntitiesCreator.createRandomUser(AccountType.AUDITOR);
		userDao.save(auditor);
		subscribers.put(auditor, SubscriberStatus.WAITING_ANSWER);
		cours1.setSubscribers(subscribers);
		assertEquals(userCount + 3, userDao.findAll().size());
		
		userDao.delete(auditor);
		assertNotEquals(userCount + 2, userDao.findAll().size());
		*/
	}

	@Test
	public void testChangeStatus() {
	
		User professor = EntitiesCreator
				.createRandomUser(AccountType.PROFESSOR);
		userDao.save(professor);
		Cours cours1 = EntitiesCreator.createRandomCours(true, professor, null);
		coursDao.save(cours1);
		Map<User, SubscriberStatus> subscribers = EntitiesCreator
				.createMapOfSubscribers(1, userDao);
		User auditor = EntitiesCreator.createRandomUser(AccountType.AUDITOR);
		userDao.save(auditor);
		subscribers.put(auditor, SubscriberStatus.WAITING_ANSWER);
		cours1.setSubscribers(subscribers);
			
		Cours cours2  = coursDao.find(cours1.getId());
		
		subscribers=cours2.getSubscribers();
		int subscribersCount = subscribers.size();
		subscribers.put(auditor, SubscriberStatus.ACCEPTED);
		coursDao.update(cours2);
		assertEquals(subscribersCount,coursDao.find(cours1.getId()).getSubscribers().size());

	}
}
