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
import com.cnam.quiz.common.config.PersistenceJPAConfig;
import com.cnam.quiz.common.dto.CoursDto;
import com.cnam.quiz.common.dto.QuestionDto;
import com.cnam.quiz.common.dto.SequenceDto;
import com.cnam.quiz.common.dto.TopicDto;
import com.cnam.quiz.common.dto.UserDto;
import com.cnam.quiz.common.enums.AccountType;
import com.cnam.quiz.common.enums.QuestionType;
import com.cnam.quiz.common.enums.SubscriberStatus;
import com.cnam.quiz.server.domain.cours.Cours;
import com.cnam.quiz.server.domain.cours.CoursDao;
import com.cnam.quiz.server.domain.cours.CoursDaoImpl;
import com.cnam.quiz.server.domain.questions.Question;
import com.cnam.quiz.server.domain.questions.QuestionDao;
import com.cnam.quiz.server.domain.questions.QuestionDaoImpl;
import com.cnam.quiz.server.domain.sequence.Sequence;
import com.cnam.quiz.server.domain.sequence.SequenceDaoImpl;
import com.cnam.quiz.server.domain.topic.Topic;
import com.cnam.quiz.server.domain.topic.TopicDao;
import com.cnam.quiz.server.domain.topic.TopicDaoImpl;
import com.cnam.quiz.server.domain.user.User;
import com.cnam.quiz.server.domain.user.UserDao;
import com.cnam.quiz.server.domain.user.UserDaoImpl;
import com.cnam.quiz.server.service.cours.CoursService;
import com.cnam.quiz.server.service.cours.CoursServiceImpl;
import com.cnam.quiz.server.service.quiz.QuizService;
import com.cnam.quiz.server.service.quiz.QuizServiceImpl;

import cnam.glg204.domain.Dao.EntitiesCreator;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { PersistenceJPAConfig.class, CoursDaoImpl.class, UserDaoImpl.class,
		CoursServiceImpl.class })

@Transactional(rollbackOn = Exception.class)
@Rollback(false)
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

		coursService.createCours(coursDto);
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

		coursService.createCours(coursDto);
		long id = coursDto.getId();

		CoursDto coursDto2 = coursService.findCours(id);
		assertEquals(coursDto.getName(), coursDto2.getName());

		coursDto.setName("Ceci est mon nom");
		coursService.updateCours(coursDto);

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
			coursService.createCours(coursDto);
			if (j == c)
				toDelete = coursDto.getId();
		}
		assertEquals(20, coursService.getAllProfessorCours(user.getId()).size());
		coursService.deleteCours(toDelete);
		assertEquals(19, coursService.getAllProfessorCours(user.getId()).size());
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
			coursService.createCours(coursDto);
			if (j == c)
				toDelete = coursDto.getId();
		}
		for (int j = 0; j < 37; j++) {
			Cours cours = EntitiesCreator.createRandomCours(false, user, null);
			CoursDto coursDto = coursToCoursDto(cours);
			coursService.createCours(coursDto);
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
		coursService.createCours(coursDto);
		long coursId1 = coursDto.getId();
		
		for( int i =0 ;i< 25 ;i++){
			User user = EntitiesCreator.createRandomUser();
			user.setAccountType(AccountType.AUDITOR);
			user.setFirstName ("auditor_"+i);
			userDao.save(user);
			coursService.suscribe(user.getId(),coursId1);
		}
		
		Cours cours2 = EntitiesCreator.createRandomCours(true, professor , null);
		CoursDto coursDto2 = coursToCoursDto(cours2);
		coursService.createCours(coursDto2);
		long coursId2 = coursDto2.getId();
		
		for( int i =0 ;i< 15 ;i++){
			User user = EntitiesCreator.createRandomUser();
			user.setAccountType(AccountType.AUDITOR);
			user.setFirstName ("auditor_"+i);
			userDao.save(user);
			coursService.suscribe(user.getId(),coursId2);
		}
		
	
		Map <UserDto, SubscriberStatus> suscribers = coursService.getAllSuscribers(coursId1);
		Map <UserDto, SubscriberStatus> suscribers2 = coursService.getAllSuscribers(coursId2);
		
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
		coursService.createCours(coursDto);
		long coursId = coursDto.getId();
		int c = (int) (Math.random() * 20);
		long toUpdate = -1;
		for( int i =0 ;i< 20 ;i++){
			User user = EntitiesCreator.createRandomUser();
			user.setAccountType(AccountType.AUDITOR);
			userDao.save(user);	
			coursService.suscribe(user.getId(),coursId);
			if (c == i)
				toUpdate = user.getId();
		}
		coursService.updateSuscriberStatus(toUpdate, coursId, SubscriberStatus.ACCEPTED);
		Map <UserDto, SubscriberStatus> suscribers = coursService.getAllSuscribers(coursId);
		
		for (Map.Entry<UserDto,SubscriberStatus> e :   suscribers.entrySet()) {
			UserDto userDto = e.getKey();
			SubscriberStatus status = e.getValue();
			if (userDto.getId() == toUpdate)
				assertEquals(SubscriberStatus.ACCEPTED ,status);
			else
				assertEquals(SubscriberStatus.WAITING_ANSWER ,status);
		}
		
		
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
