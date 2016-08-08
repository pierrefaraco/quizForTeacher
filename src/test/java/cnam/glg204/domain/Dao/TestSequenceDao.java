package cnam.glg204.domain.Dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.List;
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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { PersistenceJPAConfig.class,
		UserDaoImpl.class, TopicDaoImpl.class, QuestionDaoImpl.class,
		SequenceDaoImpl.class })
@Transactional(rollbackOn = Exception.class)
@Rollback(true)
public class TestSequenceDao  extends TestCase {
	@Autowired
	TopicDao topicDao;
	@Autowired
	UserDao userDao;
	@Autowired
	QuestionDao questionDao;
	@Autowired
	SequenceDao sequenceDao;

	@Test
	public void testAutoId() {		
		User user = EntitiesCreator.createRandomUser();
		userDao.save(user);

		Topic topic1 = EntitiesCreator.createRandomTopic(user);
		Topic topic2 = EntitiesCreator.createRandomTopic(user);
		
		topicDao.save(topic1);
		topicDao.save(topic2);

		Sequence sequence1 = EntitiesCreator.createRandomSequence(user,
				null);
		Sequence sequence2 = EntitiesCreator.createRandomSequence(user,
				null);

		
		sequenceDao.save(sequence1);
		sequenceDao.save(sequence2);

		assertNotNull(sequence1.getId());
		assertNotNull(sequence2.getId());
		assertTrue(sequence1.getId() != sequence2.getId());
	}
	
	@Test
	public void testSaveSequence() {	
		int sequenceCount = sequenceDao.findAll().size();	
		User user = EntitiesCreator.createRandomUser();
		userDao.save(user);	
		Topic topic = EntitiesCreator.createRandomTopic(user);	
		topicDao.save(topic);

		int t = 50;
		for (int i = 0;i<t;i++)
			sequenceDao.save(EntitiesCreator.createRandomSequence(user,null));
		
		List<Sequence>sequences =sequenceDao.findAll();
		
		assertEquals(sequenceCount + t ,sequences.size());	
		
	}

	@Test
	public void testUpdateSequence() {	
		int sequenceCount = sequenceDao.findAll().size();	
		User user = EntitiesCreator.createRandomUser();
		userDao.save(user);	
		Topic topic = EntitiesCreator.createRandomTopic(user);	
		topicDao.save(topic);

		int t = 3;
		for (int i = 0;i<t;i++)
			sequenceDao.save(EntitiesCreator.createRandomSequence(user,null));
		
		List<Sequence>sequences =sequenceDao.findAll();
		assertEquals(sequenceCount + t ,sequences.size());	
		Map<Integer, Question>  questions = EntitiesCreator.createListOfQuestions(questionDao,5,topic );
		for(Sequence sequence :sequences){
			sequence.setQuestions( questions);	
			sequenceDao.update(sequence);
		}
		
		List<Sequence>sequences2 =sequenceDao.findAll();
	
		for(Sequence sequence :sequences2){
			Map<Integer, Question> question2  = sequence.getQuestions();
			assertEquals(5, question2.size());
			for(int i=0;i<5;i++ ){
				assertNotNull(questions.get(i).getTopic().getUser());		
			}
		}
		
		
		
	}
	
	@Test
	public void testFindSequenceById() {
		int sequenceCount = sequenceDao.findAll().size();		
		User user = EntitiesCreator.createRandomUser();
		userDao.save(user);	
		Topic topic = EntitiesCreator.createRandomTopic(user);
		topicDao.save(topic);	
		Sequence sequence1 = EntitiesCreator.createRandomSequence(user, null );
		sequenceDao.save(sequence1);
		long id = sequence1.getId();
		assertEquals(" the sequence hasn't been registered ",sequenceCount + 1,	sequenceDao.findAll().size());	
		Sequence sequence2 = sequenceDao.find(id);
	
	}

	@Test
	public void testFindSequenceByUser() {

		User user1 = EntitiesCreator.createRandomUser();
		User user2 = EntitiesCreator.createRandomUser();
		userDao.save(user1);
		userDao.save(user2);
		int topicCount1 = topicDao.findByUser(user1).size();
		int topicCount2 = topicDao.findByUser(user2).size();
		for (int i = 0; i < 5; i++) {
			Sequence sequence = EntitiesCreator.createRandomSequence(user1, null );
			sequenceDao.save(sequence);
		}
		for (int i = 0; i < 7; i++) {
			Sequence sequence = EntitiesCreator.createRandomSequence(user2, null );
			sequenceDao.save(sequence);
		}

		
		assertEquals(topicCount1 + 5, sequenceDao.findByUser(user1).size());
		assertEquals(topicCount2 + 7, sequenceDao.findByUser(user2).size());
	}

}
