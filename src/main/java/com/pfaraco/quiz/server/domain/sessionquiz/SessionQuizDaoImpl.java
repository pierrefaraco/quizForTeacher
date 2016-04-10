package com.pfaraco.quiz.server.domain.sessionquiz;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.pfaraco.quiz.server.domain.cours.Cours;
import com.pfaraco.quiz.server.domain.user.User;
import com.pfaraco.quiz.server.util.persistence.AbstractDataAccessObjectImpl;
@Repository("sessionQuizDao")
public class SessionQuizDaoImpl extends AbstractDataAccessObjectImpl<SessionQuiz, Long> implements SessionQuizDao{

	@PostConstruct
	public void setEntityClass() {
		super.setEntityClass(SessionQuiz.class);	
	}

	@Override
	public List<SessionQuiz> findAll() {
		return em.createNamedQuery("findAllSessions").getResultList();
	}

	@Override
	public List<SessionQuiz> findByProfessor(User user) {
		Query query = em.createNamedQuery("findSessionsByProfessor");
		query.setParameter("userid", user.getId());
		return query.getResultList();
	}
	
	@Override
	public List<SessionQuiz> findByAuditor(User user) {
		Query query = em.createNamedQuery("findSessionsByAuditor");
		query.setParameter("userid", user.getId());
		return query.getResultList();
	}
	
	@Override
	public List<SessionQuiz> findByCours(Cours cours) {
		Query query = em.createNamedQuery("findSessionsByCours");
		query.setParameter("cours", cours);
		return query.getResultList();
	}

	@Override
	public List<SessionQuiz> findByAuditorAndCours(User user, Cours cours) {
		Query query = em.createNamedQuery("findSessionsByAuditor");
		query.setParameter("user", user);
		query.setParameter("coursid", cours.getId());
		return query.getResultList();
	}

}