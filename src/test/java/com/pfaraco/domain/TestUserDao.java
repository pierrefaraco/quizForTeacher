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
import com.pfaraco.quiz.server.domain.user.userDaoImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { PersistenceJPAConfig.class,userDaoImpl.class })

@Transactional
public class TestUserDao {
	@Autowired
	UserDao userDao;
	
	@Test
	public void testCreateUser() {	
		int userNb = userDao.findAll().size();		
		int t = 5;
		for (int i = 0;i<t;i++)
			userDao.save(createRandomUser());		
		assertEquals(" the 3 users haven't been created ", userNb + t ,userDao.findAll().size());					
	}
	
	@Test
	public void testFindUserById() {	
		User user1 = createRandomUser();
		long id= user1.getId();
		userDao.save(user1);
		User user2 = userDao.find(id);
		System.out.println("#" +user1.getId());
		assertTrue(" user1 did'nt find in database ",user2.equals(user1));	
	}
	
	@Test
	public void testFindAll() {	
		int userNb = userDao.findAll().size();		
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
		
	private  User  createRandomUser(){
		return createUser(
					(long)(Math.random() * 10000000),
					(int)(Math.random()* 10000 )+ "prenom",
					(int)(Math.random()* 10000) + "nom",
					"04-02-1981",
					(int)(Math.random()* 10000) + "nom@"+	(int)(Math.random() * 10000) + ".com" ,
					(int)(Math.random()* 10000) + " comentaire ",
					(int)(Math.random()* 10000) + "login",
					(int)(Math.random()* 10000) + "password", 
					+(int)(Math.random() * 3));	
	}
	
	private User createUser(long id,String firstName,String lastName,String date,String mail,String commentary,
		String login, String password, int  accountType){
			
		Date dateFormat = null;
		try {
			dateFormat = new SimpleDateFormat("dd-MM-yyyy").parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
			
		return  new User(id, firstName,lastName,dateFormat,mail,commentary,
				 login, password, accountType);
	}

}
