package com.pfaraco.quiz.server.domain.cours;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.pfaraco.quiz.server.domain.user.User;
import com.pfaraco.quiz.server.util.persistence.AbstractDataAccessObjectImpl;
@Repository("coursDao")
public class CoursDaoImpl extends AbstractDataAccessObjectImpl<Cours, Long> implements CoursDao{

	@PostConstruct
	public void setEntityClass() {
		super.setEntityClass(Cours.class);	
	}

	@Override
	public List<Cours> findAll() {
		return em.createNamedQuery("findAllCours").getResultList();
	}

	@Override
	public List<Cours> findByProfessorr(User user) {
		Query query = em.createNamedQuery("findCoursByProfessor");
		query.setParameter("userid", user.getId());
		return query.getResultList();
	}

	@Override
	public List<Cours> findByAuditor(User user) {
		Query query = em.createNamedQuery("findCoursByAuditor");
		query.setParameter("user", user);
		return query.getResultList();
	}

}
