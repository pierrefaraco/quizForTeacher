package cnam.glg204.quiz.server.domain.questions;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import cnam.glg204.quiz.server.domain.topic.Topic;
import cnam.glg204.quiz.server.domain.user.User;
import cnam.glg204.quiz.server.util.persistence.AbstractDataAccessObjectImpl;

@Repository("questionDao")
public class QuestionDaoImpl extends AbstractDataAccessObjectImpl <Question, Long> implements QuestionDao {

	@PostConstruct
	public void setEntityClass() {
		super.setEntityClass(Question.class);
	}

	@Override
	public List<Question> findByTopic(Topic topic) {
		Query query = em.createNamedQuery("findQuestionsByTopic");
		query.setParameter("topicid", topic.getId());
		return query.getResultList();
	}

	@Override
	public List<Question> findAll() {
		return em.createNamedQuery("findAllQuestions").getResultList();
	}

}
