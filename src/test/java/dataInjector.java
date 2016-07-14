import java.util.ArrayList;
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
import com.cnam.quiz.common.enums.QuestionType;
import com.cnam.quiz.server.domain.cours.Cours;
import com.cnam.quiz.server.domain.cours.CoursDao;
import com.cnam.quiz.server.domain.cours.CoursDaoImpl;
import com.cnam.quiz.server.domain.questions.Question;
import com.cnam.quiz.server.domain.questions.QuestionDao;
import com.cnam.quiz.server.domain.questions.QuestionDaoImpl;
import com.cnam.quiz.server.domain.sequence.Sequence;
import com.cnam.quiz.server.domain.sequence.SequenceDao;
import com.cnam.quiz.server.domain.sequence.SequenceDaoImpl;
import com.cnam.quiz.server.domain.topic.Topic;
import com.cnam.quiz.server.domain.topic.TopicDao;
import com.cnam.quiz.server.domain.topic.TopicDaoImpl;
import com.cnam.quiz.server.domain.user.User;
import com.cnam.quiz.server.domain.user.UserDao;
import com.cnam.quiz.server.domain.user.UserDaoImpl;
import com.cnam.quiz.server.service.cours.CoursService;
import com.cnam.quiz.server.service.cours.CoursServiceImpl;
import cnam.glg204.domain.Dao.EntitiesCreator;
import com.cnam.quiz.common.enums.SubscriberStatus;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { PersistenceJPAConfig.class,
		UserDaoImpl.class, CoursDaoImpl.class,TopicDaoImpl.class,SequenceDaoImpl.class,CoursServiceImpl.class,QuestionDaoImpl.class })
@Transactional(rollbackOn = Exception.class)
@Rollback(false)
public class dataInjector extends TestCase  {
	@Autowired
	UserDao userDao;

	@Autowired
	CoursDao coursDao;
	
	@Autowired
	TopicDao topicDao;
	
	@Autowired
	SequenceDao sequenceDao;
	
	@Autowired
	QuestionDao questionDao;
	
	@Autowired
	CoursService coursService;

	@Test
	public void testAutoId() {
		User professor =new User();
		professor.setAccountType(AccountType.PROFESSOR);	
		professor.setEmail("pierre.faraco@gmail.com");
		professor.setPassword("55261981");
		professor.setFirstName("pierre");
		professor.setLastName("Faraco");
		userDao.save(professor);
                
        User user2 =new User();
		user2.setAccountType(AccountType.PROFESSOR);	
		user2.setEmail("pierrot5526@hotmail.fr");
		user2.setPassword("55261981");
		user2.setFirstName("pierre");
		user2.setLastName("Faraco");
		userDao.save(user2);
		
		
		List <Question> questions =  new ArrayList <Question>();
		//creation des topics;   
	   	for (int j=0 ;j<20;j++){
	   		 Topic topic = EntitiesCreator.createRandomTopic(professor);
	   		 topicDao.save(topic);	 
	   		//creation des questions;  
	   	 	 for (int k=0 ;k<20;k++){
	   	 		 Question question = EntitiesCreator.createRandomQuestion(topic, QuestionType.QUIZ.QUIZ);
	   	 		 questionDao.save(question);
	   	 		 questions.add(question);
	   		 }
	   	 }
	   	 
	   	 
	   	 for (int j=0 ;j<40;j++){
	   		 Sequence sequence  = EntitiesCreator.createRandomSequence(professor, null);
	   		 sequenceDao.save(sequence);	 
	   	 }
		
		
		
		// Creation des cours;
        for (int i=0 ;i<14;i++){
                    Cours cours1 = EntitiesCreator.createRandomCours(true, professor, null);
                    if( (int)(Math.random()*4)==1)
                        cours1.setActive(false);  
                    coursDao.save(cours1);
                    Cours cours2 = EntitiesCreator.createRandomCours(true, user2, null);
                    if( i%2==1)
                        cours1.setActive(false);
                    coursDao.save(cours2);
                  
                    //creation des subscibers ;
                	for( int j =0 ;j< 40 ;j++){
                	    
            			User user1 = EntitiesCreator.createRandomUser();
            			user1.setAccountType(AccountType.AUDITOR);
            			user1.setFirstName ("auditor_"+j);
            			userDao.save(user1);
            			coursService.suscribe(user1.getId(),cours1.getId());
            			coursService.suscribe(user1.getId(),cours2.getId()); 
                                SubscriberStatus status =null;
                                switch ((int)(Math.random()*3)){
                                    case 0:status =SubscriberStatus.WAITING_ANSWER; break;
                                    case 1:status =SubscriberStatus.DENIED;break;
                                    case 2:status =  SubscriberStatus.ACCEPTED;break;
                                }
                               coursService.updateSuscriberStatus(user1.getId(),cours1.getId(),status);
                               coursService.updateSuscriberStatus(user1.getId(),cours2.getId(),status);                     
            		}

                }
    
	
	}

	
}
