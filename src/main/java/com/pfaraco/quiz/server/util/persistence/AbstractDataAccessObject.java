package com.pfaraco.quiz.server.util.persistence;
import javax.persistence.EntityManager;

import com.pfaraco.quiz.server.domain.DomainObject;


public interface AbstractDataAccessObject <E,K>  {
	EntityManager getEm() ;
	void setEm(EntityManager em);
	void save(E entity);
	void update(E entity);
	void delete(E entity);
	E find(K id);
	void setEntityClass();

}
