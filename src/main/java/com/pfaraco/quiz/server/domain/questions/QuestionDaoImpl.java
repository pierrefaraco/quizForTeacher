package com.pfaraco.quiz.server.domain.questions;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.pfaraco.quiz.server.domain.topic.Topic;
import com.pfaraco.quiz.server.domain.user.User;
import com.pfaraco.quiz.server.util.persistence.AbstractDataAccessObjectImpl;

@Repository("questionDao")
public class QuestionDaoImpl extends
		AbstractDataAccessObjectImpl<Question, Long> implements QuestionDao {

	@Override
	public void setEntityClass() {
		super.setEntityClass(Question.class);
	}

	@Override
	public List<Question> findByTopic(Topic topic) {
		Query query = em.createNamedQuery("findQuestionsByTopic");
		;
		query.setParameter("userid", topic.getId());
		return query.getResultList();
	}

	@Override
	public List<Question> findAll() {
		return em.createNamedQuery("findAllQuestions").getResultList();
	}

}
