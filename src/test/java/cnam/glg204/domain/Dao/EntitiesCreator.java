package cnam.glg204.domain.Dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import junit.framework.TestCase;

import org.hibernate.jpa.boot.internal.EntityManagerFactoryBuilderImpl;
import org.hibernate.jpa.internal.EntityManagerFactoryImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cnam.glg204.quiz.common.config.PersistenceJPAConfig;
import cnam.glg204.quiz.common.dto.QuestionDto;
import cnam.glg204.quiz.common.enums.AccountType;
import cnam.glg204.quiz.common.enums.QuestionType;
import cnam.glg204.quiz.common.enums.SessionStatus;
import cnam.glg204.quiz.common.enums.SubscriberStatus;
import cnam.glg204.quiz.server.domain.cours.Cours;
import cnam.glg204.quiz.server.domain.questions.Question;
import cnam.glg204.quiz.server.domain.questions.QuestionDao;
import cnam.glg204.quiz.server.domain.questions.QuestionDaoImpl;
import cnam.glg204.quiz.server.domain.result.Result;
import cnam.glg204.quiz.server.domain.sequence.Sequence;
import cnam.glg204.quiz.server.domain.sessionquiz.SessionQuiz;
import cnam.glg204.quiz.server.domain.topic.Topic;
import cnam.glg204.quiz.server.domain.topic.TopicDao;
import cnam.glg204.quiz.server.domain.topic.TopicDaoImpl;
import cnam.glg204.quiz.server.domain.user.User;
import cnam.glg204.quiz.server.domain.user.UserDao;
import cnam.glg204.quiz.server.domain.user.UserDaoImpl;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { PersistenceJPAConfig.class,
		UserDaoImpl.class,TopicDaoImpl.class ,QuestionDaoImpl.class })

@Transactional(rollbackOn = Exception.class)
@Rollback(true)
public class EntitiesCreator extends TestCase {
	
	static String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
	
	@Test
	public void test(){
		assertTrue(true);		
	}

	public static User createRandomUser() {
		return createUser((int) (Math.random() * 10000) + "prenom",
				(int) (Math.random() * 10000) + "nom", "04-02-1981",
				(int) (Math.random() * 10000) + "nom@"
						+ (int) (Math.random() * 10000) + ".com",
				(int) (Math.random() * 10000) + " comentaire ",
				(int) (Math.random() * 10000) + "password",
				AccountType.PROFESSOR);
	}
	
	public static User createRandomUser(AccountType accountType) {
		return createUser((int) (Math.random() * 10000) + "prenom",
				(int) (Math.random() * 10000) + "nom", "04-02-1981",
				(int) (Math.random() * 10000) + "nom@"
						+ (int) (Math.random() * 10000) + ".com",
				(int) (Math.random() * 10000) + " comentaire ",
				(int) (Math.random() * 10000) + "password",
				accountType);
	}

	public static User createUser(String firstName, String lastName,
			String date, String email, String commentary, String password,
			AccountType accountType) {


		return new User(firstName, lastName, createRandomDate(), email, commentary,
				password, accountType);
	}
	
	public static Topic createRandomTopic(User user) {

		return createTopic((int) (Math.random() * 10000) + "name",
				(int) (Math.random() * 10000) + "description", user);
	}

	public static Topic createTopic(String topicName, String description,
			User user) {
		return new Topic(topicName, description, user);
	}
	
	
	public static Map<Integer, Question> createListOfQuestions(QuestionDao questionDao,int number , Topic topic){
		Map<Integer, Question> questions = new HashMap<Integer, Question>();
		for (int i = 0; i < number; i++) {
			questions.put(i, createRandomQuestion(topic,QuestionType.QUIZ));
			questionDao.save(questions.get(i));
		}
		return questions;
	}
	
	// Pour chaque élément de la collection topics  : Crée un nombre de questions ( value Integer ) associé au Topic (key  topic)
	public static Map<Integer, Question> createListOfQuestions(QuestionDao questionDao, Map <Topic, Integer>  topics){
		
		Map<Integer, Question> questions = new HashMap<Integer, Question>();
		int c=0;
		for (Map.Entry <Topic, Integer>  e :  topics.entrySet()) {
			Topic topic = e.getKey();
			int t = e.getValue();
			for (int i = 0; i < t; i++) {
				Question question = createRandomQuestion(topic,QuestionType.QUIZ);
				questions.put(c++,question );
				questionDao.save( question );
			}
		
		}	
		return questions;
	}
	
	
	public static Question createRandomQuestion(Topic topic, QuestionType questionType) {
		Map <String,Boolean> answers =new HashMap<String,Boolean> ();
		for (int i=0;i<(int) (Math.random() * 10);i++){
			String answer =  "Proposition "+ (int)(Math.random()*10000);
			answers.put(answer ,new Random().nextBoolean());
			}
			
		
		return createQuestion((int) (Math.random() * 10000) + " title  : " + (Math.random() * 10000) + " ! ",
				(int) (Math.random() * 10000) + "question  oifzeoijopijfoijoigjroeiqjroiqjhghqoiguhqiohgiqhiucdsvjkjkvknskjvnjknkjfvnkjvkjsnjnvnskjvhreq?", 
				answers,
				(int) (Math.random() * 10),
				(int) (Math.random() * 60),
				(int) (Math.random() * 100),
				questionType,
				topic);
	}

	public static Question createQuestion(String title,String question,Map <String,Boolean> answers,int points,
			int timeToAnswer,int position, QuestionType questionType,Topic topic)
		{
		return new Question(title,question, answers , points,timeToAnswer, position,questionType, topic);
		}
	
	public static Sequence createRandomSequence(User user,Map<Integer, Question> questions) {

		return createSequence((int) (Math.random() * 10000) + "name",
				(int) (Math.random() * 10000) + "description", user,questions);
	}
	
	public static Sequence createSequence(String sequencecName, String description,
			User user,Map <Integer,Question> questions) {
		return new Sequence( sequencecName, description , user,questions);
	}
	
	public static Map <User,SubscriberStatus>  createMapOfSubscribers(int size ,UserDao userDao){
		Map <User,SubscriberStatus>  subscribers = new HashMap<User,SubscriberStatus> ();
		for (int i =0;i<size;i++){
			 User auditor = createRandomUser(AccountType.AUDITOR);
			 userDao.save(auditor);
			 subscribers.put(auditor, SubscriberStatus.WAITING_ANSWER);				
		}
		
		return subscribers ;	
	}
	
	
	public static Cours createRandomCours(Boolean active, User user,Map <User,SubscriberStatus> subscribers){
		
		return createCours((int) (Math.random() * 10000) + "nameCours", 
				(int) (Math.random() * 10000) + "descitptionCours", 
				active,
					user,subscribers);
	}
	
	public static Cours createCours(String name, String  description,boolean active,User user,Map <User,SubscriberStatus> subscribers){
		return new Cours(name, description, active, user, subscribers);
	}
	
	public static SessionQuiz createRandomSessionQuiz(SessionStatus status,Cours cours,Sequence sequence ){
	
		return createSessionQuiz(status,createRandomDate(),null,cours,sequence);				
	}

	public static SessionQuiz createSessionQuiz(SessionStatus status,Date startDate,Date endDate , Cours cours ,Sequence sequence ){
	
		return new SessionQuiz(status, startDate, endDate, cours, sequence);
	}
	
	public static Result createRandomResult(User auditor ,SessionQuiz sessionQuiz ){
		
		Map <String,boolean[]> answers = new HashMap<String,boolean[]>();
		for (int i= 0 ; i<(int) (Math.random() * 10);i++){		
			String answ = i +") "+  (Math.random()*10000000);
			boolean [] r ={new Random().nextBoolean(),new Random().nextBoolean()};
			answers.put (answ,r);	
		}
		

		
		return createResult((long) (Math.random() * 10),
						"title" + (int)(Math.random()*10),
						(int)(Math.random()*10),					
						(int)(Math.random()*20),
						(int)(Math.random()*20),
						auditor, 
						sessionQuiz, 
						"Quel est le "+  (Math.random()*10000000)+" du "+ (Math.random()*10000000) + "?", 
						answers,
						createRandomDate());
	}
	
	
	public static Result createResult(long questionId,String title,int obtainedPoints,int points, float answerTime, User auditor, SessionQuiz sessionQuiz,
			String question,Map <String,boolean[]> answers, Date date){
	
		return new Result(questionId, title, obtainedPoints,points  ,answerTime, auditor, sessionQuiz, question, answers, date);
	}
	
	
	private static Date createRandomDate(){
		Date dateFormat = null;
		SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT );
		try {
			dateFormat = formatter.parse("04-02-1981 15:32:55");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return  dateFormat 	;	
	}
}
