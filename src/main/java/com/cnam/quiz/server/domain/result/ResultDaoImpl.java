package com.cnam.quiz.server.domain.result;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.cnam.quiz.server.domain.cours.Cours;
import com.cnam.quiz.server.domain.questions.Question;
import com.cnam.quiz.server.domain.sequence.Sequence;
import com.cnam.quiz.server.domain.sessionquiz.SessionQuiz;
import com.cnam.quiz.server.domain.user.User;
import com.cnam.quiz.server.util.persistence.AbstractDataAccessObjectImpl;
@Repository("resultDao")
public class ResultDaoImpl extends AbstractDataAccessObjectImpl <Result,Long>implements ResultDao {

	@Override
	public void setEntityClass() {
		super.setEntityClass(Result.class);		
	}

	@Override
	public List<Result> findResultsByUserQuestionAndSession(long userId, long questionId, long sessionId) {
		Query query = em.createNamedQuery("findResultsByUserQuestionAndSession");
		query.setParameter("userid", userId);
		query.setParameter("questionid", questionId);
		query.setParameter("sessionid", sessionId);
		return query.getResultList();
	}

	@Override
	public List<Result> findResultsByUserAndSession(long userId, long sessionId) {
		Query query = em.createNamedQuery("findResultsByUserAndSession");
		query.setParameter("userid", userId);
		query.setParameter("sessionid", sessionId);
		return query.getResultList();
	}


	@Override
	public List<Result> findResultsBySession(long sessionId) {
		Query query = em.createNamedQuery("findResultsBySession");
		query.setParameter("sessionid", sessionId);
		return query.getResultList();
	}

	@Override
	public List<Result> findResultsByQuestionAndSession(long questionId, long sessionId) {
		Query query = em.createNamedQuery("findResultsByQuestionAndSession");
		query.setParameter("questionid", questionId);
		query.setParameter("sessionid", sessionId);
		return query.getResultList();
	}

	
}
