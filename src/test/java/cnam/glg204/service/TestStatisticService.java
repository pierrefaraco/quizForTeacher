package cnam.glg204.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cnam.quiz.common.config.PersistenceJPAConfig;
import com.cnam.quiz.common.dto.ResultDto;
import com.cnam.quiz.common.enums.AccountType;
import com.cnam.quiz.common.enums.SessionStatus;
import com.cnam.quiz.common.enums.SubscriberStatus;
import com.cnam.quiz.server.domain.cours.Cours;
import com.cnam.quiz.server.domain.cours.CoursDao;
import com.cnam.quiz.server.domain.cours.CoursDaoImpl;
import com.cnam.quiz.server.domain.questions.Question;
import com.cnam.quiz.server.domain.questions.QuestionDao;
import com.cnam.quiz.server.domain.questions.QuestionDaoImpl;
import com.cnam.quiz.server.domain.result.Result;
import com.cnam.quiz.server.domain.result.ResultDao;
import com.cnam.quiz.server.domain.result.ResultDaoImpl;
import com.cnam.quiz.server.domain.sequence.Sequence;
import com.cnam.quiz.server.domain.sequence.SequenceDao;
import com.cnam.quiz.server.domain.sequence.SequenceDaoImpl;
import com.cnam.quiz.server.domain.sessionquiz.SessionQuiz;
import com.cnam.quiz.server.domain.sessionquiz.SessionQuizDao;
import com.cnam.quiz.server.domain.sessionquiz.SessionQuizDaoImpl;
import com.cnam.quiz.server.domain.topic.TopicDao;
import com.cnam.quiz.server.domain.topic.TopicDaoImpl;
import com.cnam.quiz.server.domain.user.User;
import com.cnam.quiz.server.domain.user.UserDao;
import com.cnam.quiz.server.domain.user.UserDaoImpl;
import com.cnam.quiz.server.service.cours.CoursServiceImpl;
import com.cnam.quiz.server.service.quiz.QuizService;
import com.cnam.quiz.server.service.quiz.QuizServiceImpl;
import com.cnam.quiz.server.service.statistic.StatisticService;
import com.cnam.quiz.server.service.statistic.StatisticServiceImpl;
import com.cnam.quiz.server.service.user.UserServiceImpl;

import cnam.glg204.domain.Dao.EntitiesCreator;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { PersistenceJPAConfig.class, UserDaoImpl.class, TopicDaoImpl.class,
		SequenceDaoImpl.class, QuestionDaoImpl.class, SessionQuizDaoImpl.class, 
		CoursDaoImpl.class,SequenceDaoImpl.class, QuizServiceImpl.class,StatisticServiceImpl.class ,
		CoursServiceImpl.class,UserServiceImpl.class,ResultDaoImpl.class})

@Transactional(rollbackOn = Exception.class)
@Rollback(false)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestStatisticService {
	
	@Autowired
	StatisticService statisticService;

	@Autowired
	UserDao userDao;

	@Autowired
	TopicDao topicDao;

	@Autowired
	QuestionDao questionDao;
	
	@Autowired
	CoursDao coursDao;
	
	@Autowired
	SequenceDao sequenceDao;
	
	@Autowired
	SessionQuizDao sessionQuizDao;

	
	
	static long cours1Id = 0 ;
	static long cours2Id = 0 ;
	static long sessionQuiz1Id = 0 ;
	static long sessionQuiz2Id = 0 ;
	static long sessionQuiz3Id = 0 ;
	static long sessionQuiz4Id = 0 ;
	static List<Long> auditorsId = new ArrayList<Long> ();;
	
	@Test
	public void  acreateEnvironmentForTests(){
		User user = EntitiesCreator.createRandomUser(AccountType.PROFESSOR);
		userDao.save(user);
		
		Cours cours1 = EntitiesCreator.createRandomCours(true, user, null);
		coursDao.save(cours1);
		Cours cours2 = EntitiesCreator.createRandomCours(true, user, null);
		coursDao.save(cours2);
		
		Sequence sequence = EntitiesCreator.createRandomSequence(user, null);
		sequenceDao.save(sequence);
		
		SessionQuiz sessionQuiz1 = EntitiesCreator.createRandomSessionQuiz(
				SessionStatus.RUNNING, cours1, sequence);	
		sessionQuizDao.save(sessionQuiz1 );
		SessionQuiz sessionQuiz2 = EntitiesCreator.createRandomSessionQuiz(
				SessionStatus.RUNNING, cours1, sequence);
		sessionQuizDao.save(sessionQuiz2 );
		SessionQuiz sessionQuiz3 = EntitiesCreator.createRandomSessionQuiz(
				SessionStatus.RUNNING, cours2, sequence);	
		sessionQuizDao.save(sessionQuiz3 );
		SessionQuiz sessionQuiz4 = EntitiesCreator.createRandomSessionQuiz(
				SessionStatus.RUNNING, cours2, sequence);
		sessionQuizDao.save(sessionQuiz4 );
		
		cours1Id = cours1.getId() ;
		cours2Id = cours2.getId()  ;
		sessionQuiz1Id = sessionQuiz1.getId() ;
		sessionQuiz2Id = sessionQuiz2.getId() ;
		sessionQuiz3Id = sessionQuiz3.getId() ;
		sessionQuiz4Id = sessionQuiz4.getId() ;	
		
		for ( int i = 0 ; i<20 ; i++){
			User auditor = EntitiesCreator.createRandomUser(AccountType.AUDITOR);
			userDao.save( auditor );	
			auditorsId.add(auditor.getId());
		} 		
	}
	
	@Test
	public void testSaveResult(){
		for (long auditorId : auditorsId){
			ResultDto resultDto = createResultDto ( auditorId,sessionQuiz1Id);
			statisticService.saveResult(resultDto);
		}
		assertEquals(20,statisticService.findResultsBySession(sessionQuiz1Id).size());
		
		for (int i = 0;i<2 ;i++ )
			for (long auditorId : auditorsId){
				ResultDto resultDto = createResultDto ( auditorId,sessionQuiz2Id);
				statisticService.saveResult(resultDto);
			}
		assertEquals(20,statisticService.findResultsBySession(sessionQuiz1Id).size());
		assertEquals(40,statisticService.findResultsBySession(sessionQuiz2Id).size());	
		assertEquals(60, statisticService.findResultsByCours(cours1Id).size());
		
		for (long auditorId : auditorsId){
			ResultDto resultDto = createResultDto ( auditorId,sessionQuiz3Id);
			statisticService.saveResult(resultDto);
		}
		
		for (int i = 0;i<2 ;i++ )
			for (long auditorId : auditorsId){
				ResultDto resultDto = createResultDto ( auditorId,sessionQuiz4Id);
				statisticService.saveResult(resultDto);
			}
		
		assertEquals(20,statisticService.findResultsBySession(sessionQuiz1Id).size());
		assertEquals(40,statisticService.findResultsBySession(sessionQuiz2Id).size());	
		assertEquals(20,statisticService.findResultsBySession(sessionQuiz3Id).size());
		assertEquals(40,statisticService.findResultsBySession(sessionQuiz4Id).size());	
		assertEquals(60, statisticService.findResultsByCours(cours1Id).size());
		assertEquals(60, statisticService.findResultsByCours(cours2Id).size());
		
		for (long auditorId : auditorsId){
			assertEquals(3,statisticService.findResultsByUserAndCours(auditorId , cours1Id).size());
			assertEquals(3,statisticService.findResultsByUserAndCours(auditorId , cours2Id).size());
			assertEquals(1,statisticService.findResultsByUserAndSession(auditorId, sessionQuiz1Id).size());
			assertEquals(2,statisticService.findResultsByUserAndSession(auditorId, sessionQuiz2Id).size());
			assertEquals(1,statisticService.findResultsByUserAndSession(auditorId, sessionQuiz3Id).size());
			assertEquals(2,statisticService.findResultsByUserAndSession(auditorId, sessionQuiz4Id).size());
		}
		
	}
	

	public ResultDto createResultDto (long auditorId,long sessionId){
		ResultDto resultDto = new ResultDto ();
		resultDto.setUserId(auditorId);
		resultDto.setAnswerTime((int) (Math.random()*20 ));
		resultDto.setQuestionId((long) (Math.random()*2000000 ) );
		resultDto.setQuestion("question " + (int) (Math.random()*2000000 ));
		List <String> propositions = getPropositions();
		resultDto.setPropositions( propositions);
		resultDto.setGivenAnswers(	selectSomePropositions(propositions));
		resultDto.setGoodAnswers (	selectSomePropositions(propositions));
		resultDto.setSessionQuizId(sessionId);
		int maxPoint = (int) (Math.random()* 10 );
		resultDto.setMaxPoints( maxPoint);
		resultDto.setObtainedPoints((int) (Math.random()*  maxPoint  ));
		return resultDto;	
	}
	
	
	
	private List <String>  getPropositions(){	
		List <String> propositions = new ArrayList<String>();
		for (int i = 0 ;i<(int)(Math.random()*10);i++)
			 propositions.add(i +" => proposition " +Math.random()*1000000  );
		return  propositions;		
		
	}
	
	private List <String>  selectSomePropositions(List<String> propositions ){	
		List <String> selectedPropositions = new ArrayList<String>();
		for (String givenAnswer : propositions)
			if(Math.random()*10>5)
				 selectedPropositions.add(givenAnswer );
		return selectedPropositions;		
		
	}
}
