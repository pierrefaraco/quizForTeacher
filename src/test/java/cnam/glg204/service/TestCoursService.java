package cnam.glg204.service;

import javax.transaction.Transactional;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import cnam.glg204.quiz.common.config.PersistenceJPAConfig;
import cnam.glg204.quiz.common.dto.CoursDto;
import cnam.glg204.quiz.common.dto.CoursWithStatusDto;
import cnam.glg204.quiz.common.dto.QuestionDto;
import cnam.glg204.quiz.common.dto.SequenceDto;
import cnam.glg204.quiz.common.dto.TopicDto;
import cnam.glg204.quiz.common.dto.UserDto;
import cnam.glg204.quiz.common.enums.AccountType;
import cnam.glg204.quiz.common.enums.QuestionType;
import cnam.glg204.quiz.common.enums.SubscriberStatus;
import cnam.glg204.quiz.common.exceptions.CheckException;
import cnam.glg204.quiz.server.domain.cours.Cours;
import cnam.glg204.quiz.server.domain.cours.CoursDao;
import cnam.glg204.quiz.server.domain.cours.CoursDaoImpl;
import cnam.glg204.quiz.server.domain.questions.Question;
import cnam.glg204.quiz.server.domain.questions.QuestionDao;
import cnam.glg204.quiz.server.domain.questions.QuestionDaoImpl;
import cnam.glg204.quiz.server.domain.sequence.Sequence;
import cnam.glg204.quiz.server.domain.sequence.SequenceDaoImpl;
import cnam.glg204.quiz.server.domain.topic.Topic;
import cnam.glg204.quiz.server.domain.topic.TopicDao;
import cnam.glg204.quiz.server.domain.topic.TopicDaoImpl;
import cnam.glg204.quiz.server.domain.user.User;
import cnam.glg204.quiz.server.domain.user.UserDao;
import cnam.glg204.quiz.server.domain.user.UserDaoImpl;
import cnam.glg204.quiz.server.service.cours.CoursService;
import cnam.glg204.quiz.server.service.cours.CoursServiceImpl;
import cnam.glg204.quiz.server.service.quiz.QuizService;
import cnam.glg204.quiz.server.service.quiz.QuizServiceImpl;
import cnam.glg204.quiz.server.websocket.WebSocketPoolManager;

import cnam.glg204.domain.Dao.EntitiesCreator;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { PersistenceJPAConfig.class, CoursDaoImpl.class, UserDaoImpl.class,
		CoursServiceImpl.class, WebSocketPoolManager.class })

@Transactional(rollbackOn = Exception.class)
@Rollback(true)
public class TestCoursService {
	@Autowired
	CoursService coursService;

	@Autowired
	UserDao userDao;

	@Autowired
	CoursDao coursDao;

	@Test
	public void testCreateCours() {
		User user = EntitiesCreator.createRandomUser();
		user.setAccountType(AccountType.PROFESSOR);
		userDao.save(user);
		Cours cours = EntitiesCreator.createRandomCours(true, user, null);
		CoursDto coursDto = coursToCoursDto(cours);

		try {
			coursService.createCours(coursDto);
		} catch (CheckException e) {
			fail();
		}
		long id = coursDto.getId();

		CoursDto coursDto2 = coursService.findCours(id);

		assertEquals(coursDto.getId(), coursDto2.getId());
		assertEquals(coursDto.getName(), coursDto2.getName());
		assertEquals(user.getId(), coursDto2.getUserId());
		assertEquals(true, coursDto2.isActive());
	}

	@Test
	public void testUpdateCours() {
		User user = EntitiesCreator.createRandomUser();
		user.setAccountType(AccountType.PROFESSOR);
		userDao.save(user);
		Cours cours = EntitiesCreator.createRandomCours(true, user, null);
		CoursDto coursDto = coursToCoursDto(cours);

		try {
			coursService.createCours(coursDto);
		} catch (CheckException e) {	
			fail();
		}
		long id = coursDto.getId();

		CoursDto coursDto2 = coursService.findCours(id);
		assertEquals(coursDto.getName(), coursDto2.getName());

		coursDto.setName("Ceci est mon nom");
		try {
			coursService.updateCours(coursDto);
		} catch (CheckException e) {
			fail();
		}

		assertNotEquals(coursDto.getName(), coursDto2.getName());

		coursDto2 = coursService.findCours(id);
		assertEquals(coursDto.getName(), coursDto2.getName());
		assertEquals(coursDto.getId(), coursDto2.getId());
		assertEquals(coursDto.getName(), coursDto2.getName());
		assertEquals(user.getId(), coursDto2.getUserId());
		assertEquals(true, coursDto2.isActive());
	}

	@Test
	public void testDeleteCours() {
		User user = EntitiesCreator.createRandomUser();
		user.setAccountType(AccountType.PROFESSOR);
		userDao.save(user);
		int c = (int) (Math.random() * 20);
		long toDelete = -1;
		for (int j = 0; j < 20; j++) {
			Cours cours = EntitiesCreator.createRandomCours(true, user, null);
			CoursDto coursDto = coursToCoursDto(cours);
			try {
				coursService.createCours(coursDto);
			} catch (CheckException e) {
				fail();
			}
			if (j == c)
				toDelete = coursDto.getId();
		}
		assertEquals(20, coursService.findCoursByProfessor(user.getId()).size());
		coursService.deleteCours(toDelete);
		assertEquals(19, coursService.findCoursByProfessor(user.getId()).size());
	}
	
	
	@Test
	public void testGetActivesCours() {
		int t =  coursService.getAllActiveCours().size();
		User user = EntitiesCreator.createRandomUser();
		user.setAccountType(AccountType.PROFESSOR);
		userDao.save(user);
		int c = (int) (Math.random() * 20);
		long toDelete = -1;
		for (int j = 0; j < 18; j++) {
			Cours cours = EntitiesCreator.createRandomCours(true, user, null);
			CoursDto coursDto = coursToCoursDto(cours);
			try {
				coursService.createCours(coursDto);
			} catch (CheckException e) {
				fail();
			}
			if (j == c)
				toDelete = coursDto.getId();
		}
		for (int j = 0; j < 37; j++) {
			Cours cours = EntitiesCreator.createRandomCours(false, user, null);
			CoursDto coursDto = coursToCoursDto(cours);
			try {
				coursService.createCours(coursDto);
			} catch (CheckException e) {
				fail();
			}
			if (j == c)
				toDelete = coursDto.getId();
		}
		
		assertEquals(t + 18 , coursService.getAllActiveCours().size());
		
	}
	
	@Test
	public void testSuscribeToCours() {
		
		User professor = EntitiesCreator.createRandomUser();
		professor.setAccountType(AccountType.PROFESSOR);
		userDao.save(professor);
		
		Cours cours = EntitiesCreator.createRandomCours(true, professor , null);
		CoursDto coursDto = coursToCoursDto(cours);
		try {
			coursService.createCours(coursDto);
		} catch (CheckException e) {
			fail();
		}
		long coursId1 = coursDto.getId();
		
		for( int i =0 ;i< 25 ;i++){
			User user = EntitiesCreator.createRandomUser();
			user.setAccountType(AccountType.AUDITOR);
			user.setFirstName ("auditor_"+i);
			userDao.save(user);
			try {
				coursService.suscribe(user.getId(),coursId1);
			} catch (CheckException e) {
				fail();
			}
		}
		
		Cours cours2 = EntitiesCreator.createRandomCours(true, professor , null);
		CoursDto coursDto2 = coursToCoursDto(cours2);
		try {
			coursService.createCours(coursDto2);
		} catch (CheckException e) {
			fail();
		}
		long coursId2 = coursDto2.getId();
		
		for( int i =0 ;i< 15 ;i++){
			User user = EntitiesCreator.createRandomUser();
			user.setAccountType(AccountType.AUDITOR);
			user.setFirstName ("auditor_"+i);
			userDao.save(user);
			try {
				coursService.suscribe(user.getId(),coursId2);
			} catch (CheckException e) {
				fail();
			}
		}
		
	
		Map <UserDto, SubscriberStatus> suscribers = coursService.getCourWithSuscribers(coursId1).getSubscribers();
		Map <UserDto, SubscriberStatus> suscribers2 = coursService.getCourWithSuscribers(coursId2).getSubscribers();
		
		assertEquals(25,suscribers.size());	
		assertEquals(15,suscribers2.size());
	
		}
	
	@Test
	public void testUpdateSuscriberStatsus() {
		User professor = EntitiesCreator.createRandomUser();
		professor.setAccountType(AccountType.PROFESSOR);
		userDao.save(professor);
		
		Cours cours = EntitiesCreator.createRandomCours(true, professor , null);
		CoursDto coursDto = coursToCoursDto(cours);
		try {
			coursService.createCours(coursDto);
		} catch (CheckException e1) {
			e1.printStackTrace();
		}
		long coursId = coursDto.getId();
		int c = (int) (Math.random() * 20);
		long toUpdate = -1;
		for( int i =0 ;i< 20 ;i++){
			User user = EntitiesCreator.createRandomUser();
			user.setAccountType(AccountType.AUDITOR);
			userDao.save(user);	
			try {
				coursService.suscribe(user.getId(),coursId);
			} catch (CheckException e1) {
				e1.printStackTrace();
			}
			if (c == i)
				toUpdate = user.getId();
		}
		try {
			coursService.updateSuscriberStatus(toUpdate, coursId, SubscriberStatus.ACCEPTED);
		} catch (CheckException e1) {
			
			e1.printStackTrace();
		}
		Map <UserDto, SubscriberStatus> suscribers = coursService.getCourWithSuscribers(coursId).getSubscribers();
		
		for (Map.Entry<UserDto,SubscriberStatus> e :   suscribers.entrySet()) {
			UserDto userDto = e.getKey();
			SubscriberStatus status = e.getValue();
			if (userDto.getId() == toUpdate)
				assertEquals(SubscriberStatus.ACCEPTED ,status);
			else
				assertEquals(SubscriberStatus.WAITING_ANSWER ,status);
		}
		
		
	}
	
	@Test
	public void testGetAllCoursWithStatus() {
		
		User professor = EntitiesCreator.createRandomUser();
		professor.setAccountType(AccountType.PROFESSOR);
		userDao.save(professor);
		
		User auditor = EntitiesCreator.createRandomUser();
		userDao.save(auditor);
		
		int x = (int)(Math.random()*20);
		for (int i =0 ;i <20 ;i++ ){
			Cours cours = EntitiesCreator.createRandomCours(true, professor , null);
			CoursDto coursDto = coursToCoursDto(cours);
			try {
				coursService.createCours(coursDto);
			} catch (CheckException e) {
				fail();
			}
			if(x>i)
				try {
					coursService.suscribe(auditor .getId(),	 coursDto.getId());
				} catch (CheckException e) {
					fail();
				}
		}
		
		
		List <CoursWithStatusDto> listCours = coursService. findAllCoursWithAuditorStatus(auditor .getId());
		int y =0;
		for (CoursWithStatusDto cours :listCours)
			if ( cours.getStatus() == SubscriberStatus.WAITING_ANSWER)
				y++;
		assertEquals(x,y);	
	}

	public CoursDto coursToCoursDto(Cours cours) {
		CoursDto coursDto = new CoursDto();
		coursDto.setId(cours.getId());
		coursDto.setActive(cours.isActive());
		coursDto.setDescription(cours.getDescription());
		coursDto.setName(cours.getName());
		long userId = cours.getUser().getId();
		coursDto.setUserId(userId);
		return coursDto;
	}
}
