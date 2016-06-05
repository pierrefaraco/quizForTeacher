package com.cnam.quiz.server.domain.result;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.cnam.quiz.server.domain.cours.Cours;
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
	public List<Result> findResultForASessionQuiz(SessionQuiz sessionQuiz) {
		Query query = em.createNamedQuery("findResultsForASession");
		query.setParameter("sessionId", sessionQuiz.getId());
		return query.getResultList();
	}

	@Override
	public List<Result> findResultForACours(Cours cours) {
		Query query = em.createNamedQuery("findResultsByUserForACours");
		query.setParameter("coursId", cours.getId());
		return query.getResultList();
	}

	@Override
	public List<Result> findResultByUserForASessionQuiz(SessionQuiz sessionQuiz, User user) {
		Query query = em.createNamedQuery("findResultsByUserForASession");
		query.setParameter("userId", user.getId());
		query.setParameter("sessionId", sessionQuiz.getId());
		return query.getResultList();
	}

	@Override
	public List<Result> findResultByUserForACours(Cours cours, User user) {
		Query query = em.createNamedQuery("findResultsByUserForACours");
		query.setParameter("userId", user.getId());
		query.setParameter("coursId", cours.getId());
		return query.getResultList();
	}
	
	

	
}
