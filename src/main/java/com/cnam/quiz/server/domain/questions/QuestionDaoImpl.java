package com.cnam.quiz.server.domain.questions;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.cnam.quiz.server.domain.topic.Topic;
import com.cnam.quiz.server.domain.user.User;
import com.cnam.quiz.server.util.persistence.AbstractDataAccessObjectImpl;

@Repository("questionDao")
public class QuestionDaoImpl extends AbstractDataAccessObjectImpl <Question, Long> implements QuestionDao {

	@PostConstruct
	public void setEntityClass() {
		super.setEntityClass(Question.class);
	}

	@Override
	public List<Question> findQuestionsByTopic(Topic topic) {
		Query query = em.createNamedQuery("findQuestionsByTopic");
		query.setParameter("topicid", topic.getId());
		return query.getResultList();
	}

	@Override
	public List<Question> findAll() {
		return em.createNamedQuery("findAllQuestions").getResultList();
	}

}
