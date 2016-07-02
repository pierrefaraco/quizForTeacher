package com.cnam.quiz.server.util.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;

public abstract class AbstractDataAccessObjectImpl <E,K>  implements AbstractDataAccessObject <E,K> {
	@PersistenceContext
	protected EntityManager em;
   // protected EntityTransaction _tx;
    private Class <E> entityClass;
    public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

	public void setEntityClass(Class<E> entityClass) {
	        this.entityClass = entityClass;
	}
	
	@Override
	public void save(E entity) {
		em.persist(entity);// TODO Auto-generated method stub
		
	}

	@Override
	public void update(E entity) {
		  em.merge(entity);
	}

	@Override
	public void delete(E entity) {
		em.remove(entity);
		
	}

	@Override
	public E find(K id) {				
		return em.find(entityClass , id);
	}
	
	@Override
	public void refresh(E entity){
			em.refresh(entity);
	}		
}