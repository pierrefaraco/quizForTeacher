package cnam.glg204.domain.Dao;


import java.util.ArrayList;
import java.util.Date;
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
import com.cnam.quiz.server.domain.questions.QuestionDaoImpl;
import com.cnam.quiz.server.domain.topic.TopicDaoImpl;
import com.cnam.quiz.server.domain.user.User;
import com.cnam.quiz.server.domain.user.UserDao;
import com.cnam.quiz.server.domain.user.UserDaoImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { PersistenceJPAConfig.class,
		UserDaoImpl.class,TopicDaoImpl.class ,QuestionDaoImpl.class })

@Transactional(rollbackOn = Exception.class)
@Rollback(true)
public class TestUserDao  extends TestCase{
	@Autowired
	UserDao userDao;
	
	@Test
	public void testAutoId() {	
		User user = EntitiesCreator.createRandomUser();	
		User user2 = EntitiesCreator.createRandomUser();	
		userDao.save(user);
		userDao.save(user2);
		assertNotNull(user .getId());
		assertNotNull(user2 .getId());
		assertTrue(user .getId()!= user2 .getId());
	}
		
	@Test
	public void testSaveUser() {	
		int usersCount = userDao.findAll().size();		
		int t = 10;
		for (int i = 0;i<t;i++)
			userDao.save(EntitiesCreator.createRandomUser());		
		assertEquals(" the 3 users haven't been registered ", usersCount + t ,userDao.findAll().size());					
	}
	
	@Test
	public void testSavePierre() {	
		User user =new User();
		user.setAccountType(AccountType.AUDITOR);	
		user.setEmail("pierre.faraco@gmail.com");
		user.setPassword("55261981");
		user.setFirstName("pierre");
		user.setLastName("Faraco");
		userDao.save(user);		
						
	}
	
	@Test
	public void testDeleteUser() {	
		int usersCount = userDao.findAll().size();			
		User user = EntitiesCreator.createRandomUser();
		userDao.delete(user);
		assertEquals(" no user should be registered  ", usersCount   ,userDao.findAll().size());
		userDao.save(user);	
		assertEquals(" the user hasn't been registered ", usersCount + 1 ,userDao.findAll().size());
		userDao.delete(user);
		assertEquals(" the user hasn't been registered ", usersCount  ,userDao.findAll().size());		
	}
	
	@Test
	public void testUpdateUser() {	
		int usersCount = userDao.findAll().size();		
		User user = EntitiesCreator.createRandomUser();		
		userDao.save(user);
		long id= user.getId();
		assertEquals(" the user hasn't been registered ", usersCount + 1 ,userDao.findAll().size());
		User user2 = EntitiesCreator.createRandomUser();
		user2.setId(id);
		userDao.update(user2);
		assertEquals(" the user hasn't been merged ", usersCount + 1 ,userDao.findAll().size());
		User user3 = EntitiesCreator.createRandomUser();
		userDao.update(user3);
		assertEquals(" the user has been merged ", usersCount + 2 ,userDao.findAll().size());
		
	}
	
	
	@Test
	public void testFindUserById() {	
		int usersCount = userDao.findAll().size();	
		User user1 = EntitiesCreator.createRandomUser();	
		userDao.save(user1);
		long id= user1.getId();
		assertEquals(" the user hasn't been registered ", usersCount + 1 ,userDao.findAll().size());
		User user2 = userDao.find(id);
		assertTrue(" user1 did'nt find in database ",user2.equals(user1));	
	}
		
	
	@Test
	public void testFindAll() {		
		int t = 4;
		List <User> users  = new ArrayList();
		for (int i = 0;i<t;i++){
			User user = EntitiesCreator.createRandomUser();
			users.add(user);
			userDao.save(user);
		}	
		List <User> users2  = userDao.findAll();
		
		for (int i = 0;i<t;i++)
			assertTrue(" user "+ i +" did'nt find in database ",users2.contains(users.get(i)));	
												
	}
	
	@Test
	public void testFindByEmailAndPassword(){
		User user = EntitiesCreator.createRandomUser();	
		String email = user.getEmail();
		String password = user.getPassword();
		System.out.println(email + " " + password);
		userDao.save(user);	
		assertEquals(userDao.findUserByMailAndPassword(email, password).getEmail(), email );	
		assertEquals(userDao.findUserByMailAndPassword(email, password).getPassword(), password);	
	}
	


}
