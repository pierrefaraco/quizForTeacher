package com.pfaraco.domain;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.pfaraco.quiz.server.config.PersistenceJPAConfig;
import com.pfaraco.quiz.server.domain.user.User;
import com.pfaraco.quiz.server.domain.user.UserDao;
import com.pfaraco.quiz.server.domain.user.UserDaoImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { PersistenceJPAConfig.class,UserDaoImpl.class })

@Transactional
public class TestUserDao {
	@Autowired
	UserDao userDao;
	
	@Test
	public void testAutoId() {	
		User user = createRandomUser();	
		User user2 = createRandomUser();	
		userDao.save(user);
		userDao.save(user2);
		assertNotNull(user .getId());
		assertNotNull(user2 .getId());
		assertTrue(user .getId()!= user2 .getId());
	}
		
	@Test
	public void testCreateUser() {	
		int usersCount = userDao.findAll().size();		
		int t = 5;
		for (int i = 0;i<t;i++)
			userDao.save(createRandomUser());		
		assertEquals(" the 3 users haven't been registered ", usersCount + t ,userDao.findAll().size());					
	}
	
	@Test
	public void testDeleteUser() {	
		int usersCount = userDao.findAll().size();			
		User user = createRandomUser();
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
		User user = createRandomUser();		
		userDao.save(user);
		long id= user.getId();
		assertEquals(" the user hasn't been registered ", usersCount + 1 ,userDao.findAll().size());
		User user2 = createRandomUser();
		user2.setId(id);
		userDao.update(user2);
		assertEquals(" the user hasn't been merged ", usersCount + 1 ,userDao.findAll().size());
		User user3 = createRandomUser();
		userDao.update(user3);
		assertEquals(" the user has been merged ", usersCount + 2 ,userDao.findAll().size());
		
	}
	
	
	@Test
	public void testFindUserById() {	
		int usersCount = userDao.findAll().size();	
		User user1 = createRandomUser();	
		userDao.save(user1);
		long id= user1.getId();
		assertEquals(" the user hasn't been registered ", usersCount + 1 ,userDao.findAll().size());
		User user2 = userDao.find(id);
		assertTrue(" user1 did'nt find in database ",user2.equals(user1));	
	}
		
	
	@Test
	public void testFindAll() {		
		int t = 1000;
		List <User> users  = new ArrayList();
		for (int i = 0;i<t;i++){
			User user = createRandomUser();
			users.add(user);
			userDao.save(user);
		}	
		List <User> users2  = userDao.findAll();
		
		for (int i = 0;i<t;i++)
			assertTrue(" user "+ i +" did'nt find in database ",users2.contains(users.get(i)));	
												
	}
	
	@Test
	public void testFindByEmailAndPassword(){
		User user = createRandomUser();	
		String email = user.getEmail();
		String password = user.getPassword();
		System.out.println(email + " " + password);
		userDao.save(user);	
		assertEquals(userDao.findUserByMailAndPassword(email, password).getEmail(), email );	
		assertEquals(userDao.findUserByMailAndPassword(email, password).getPassword(), password);	
	}
	
		
	public static  User  createRandomUser(){
		return createUser(
					(int)(Math.random()* 10000 )+ "prenom",
					(int)(Math.random()* 10000) + "nom",
					"04-02-1981",
					(int)(Math.random()* 10000) + "nom@"+	(int)(Math.random() * 10000) + ".com" ,
					(int)(Math.random()* 10000) + " comentaire ",					
					(int)(Math.random()* 10000) + "password", 
					+(int)(Math.random() * 3));	
	}
	
	private  static User createUser(String firstName,String lastName,String date,String email,String commentary,
		String password, int  accountType){
			
		Date dateFormat = null;
		try {
			dateFormat = new SimpleDateFormat("dd-MM-yyyy").parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
			
		return  new User( firstName,lastName,dateFormat,email,commentary,
				 password, accountType);
	}

}
